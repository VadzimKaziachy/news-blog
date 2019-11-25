package news.blog.com.service.impl;

import com.tabasoft.converter.api.ExtendedConversionService;

import lombok.AllArgsConstructor;

import news.blog.com.exception.BadRequestException;
import news.blog.com.model.UserEntity;
import news.blog.com.model.type.UserRole;
import news.blog.com.repository.UserRepository;
import news.blog.com.service.dto.UserDto;
import news.blog.com.service.RegistrationService;

import news.blog.com.service.dto.responseDto.UserProfileDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ExtendedConversionService conversionService;
    private final AuthorizationServerEndpointsConfiguration configuration;


    @Override
    @Transactional
    public UserProfileDto registrationUser(UserDto userDto)
    {

        if (userRepository.existsByLogin(userDto.getLogin()))
        {
            throw new BadRequestException("This user exists.");
        }

        UserEntity user =  userRepository.save(UserEntity.builder()
                                                         .login(userDto.getLogin())
                                                         .password(passwordEncoder.encode(userDto.getPassword()))
                                                         .role(UserRole.USER)
                                                         .build()
        );
        UserProfileDto profileUserDto  = conversionService.convert(user, UserProfileDto.class);
        OAuth2AccessToken accessToken = getAccessToken(user.getLogin(), "web");

        profileUserDto.setAccessToken(accessToken.getValue());

        return profileUserDto;
    }

    @Override
    public OAuth2AccessToken getAccessToken(String userName, String clientId)
    {
        HashMap<String, String> authorizationParameters = new HashMap<>();
        authorizationParameters.put("scope", "read");
        authorizationParameters.put("username", userName);
        authorizationParameters.put("client_id", clientId);
        authorizationParameters.put("grant", "password");

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        Set<String> responseType = new HashSet<>();
        responseType.add("password");

        Set<String> scopes = new HashSet<>();
        scopes.add("read");
        scopes.add("write");

        OAuth2Request authorizationRequest = new OAuth2Request(
                authorizationParameters, "web",
                authorities, true,scopes, null, "",
                responseType, null);

        User userPrincipal = new User(userName, "", true, true, true, true, authorities);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userPrincipal, null, authorities);

        OAuth2Authentication authenticationRequest = new OAuth2Authentication(authorizationRequest, authenticationToken);
        authenticationRequest.setAuthenticated(true);

        AuthorizationServerTokenServices tokenService = configuration.getEndpointsConfigurer().getTokenServices();

        return tokenService.createAccessToken(authenticationRequest);
    }
}
