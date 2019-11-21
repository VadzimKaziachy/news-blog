package news.blog.com.service.impl;

import com.tabasoft.converter.api.ExtendedConversionService;
import lombok.RequiredArgsConstructor;
import news.blog.com.model.UserEntity;
import news.blog.com.repository.UserRepository;
import news.blog.com.service.dto.BlogUserDetails;
import news.blog.com.service.dto.UserDto;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userDetailsService")
@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ExtendedConversionService conversionService;

    @Override
    public UserDetails loadUserByUsername(String login)
    {
        UserEntity user = userRepository.findByLogin(login);
        UserDto userDto = conversionService.convert(user, UserDto.class);
        return new BlogUserDetails(userDto);
    }
}
