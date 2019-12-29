package news.blog.com.service;

import news.blog.com.service.dto.UserDto;
import news.blog.com.service.dto.response.UserProfileResponseDto;

import java.util.Collection;

public interface UserService
{
    UserProfileResponseDto getUserProfile();

    void saveUser(UserDto user);

    Collection<UserProfileResponseDto> getUsersProfile();
}
