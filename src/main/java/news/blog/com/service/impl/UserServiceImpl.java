package news.blog.com.service.impl;

import com.tabasoft.converter.api.ExtendedConversionService;
import lombok.AllArgsConstructor;
import news.blog.com.exception.UnauthorizedException;
import news.blog.com.model.UserEntity;
import news.blog.com.model.type.UserRole;
import news.blog.com.repository.UserRepository;
import news.blog.com.service.ContextService;
import news.blog.com.service.CurrentUserProvider;
import news.blog.com.service.SecurityService;
import news.blog.com.service.UserService;
import news.blog.com.service.dto.UserDto;
import news.blog.com.service.dto.responseDto.UserProfileDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserService, CurrentUserProvider
{
    @Resource(name = "userRepository")
    private UserRepository userRepository;
    @Resource(name = "contextService")
    private ContextService contextService;
    @Resource(name = "securityService")
    private SecurityService securityService;
    @Resource(name = "extendedConversionService")
    private ExtendedConversionService conversionService;

    @Override
    public UserProfileDto getUserProfile()
    {
        UserEntity userEntity = securityService.getUserEntity();
        return conversionService.convert(userEntity, UserProfileDto.class);
    }

    @Override
    public void saveUser(UserDto user)
    {
        UserEntity userEntity = securityService.getUserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userRepository.save(userEntity);
    }

    @Override
    public Collection<UserProfileDto> getUsersProfile() {
        contextService.assertCurrentUserHasAdminRole();
        return conversionService.convertMany(
                userRepository.findAll()
                              .stream()
                              .filter(user -> user.getRole().equals(UserRole.USER))
                              .collect(Collectors.toList()),
                UserProfileDto.class);
    }

    @Override
    public boolean isCurrentUserHasAdminRole()
    {
        UserEntity user = securityService.getUserEntity();
        if (user == null)
        {
            throw new UnauthorizedException("User is unauthorized");
        }
        return UserRole.ADMIN.equals(user.getRole());
    }
}
