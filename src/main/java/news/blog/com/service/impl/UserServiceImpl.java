package news.blog.com.service.impl;

import com.tabasoft.converter.api.ExtendedConversionService;
import lombok.AllArgsConstructor;
import news.blog.com.model.UserEntity;
import news.blog.com.model.type.UserRole;
import news.blog.com.repository.UserRepository;
import news.blog.com.service.UserService;
import news.blog.com.service.dto.UserDto;
import news.blog.com.service.dto.responseDto.UserProfileDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final SecurityServiceImpl securityService;
    private final ExtendedConversionService conversionService;

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
        return conversionService.convertMany(
                userRepository.findAll()
                              .stream()
                              .filter(user -> user.getRole().equals(UserRole.USER))
                              .collect(Collectors.toList()),
                UserProfileDto.class);
    }
}
