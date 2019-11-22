package news.blog.com.service;

import news.blog.com.service.dto.UserDto;
import news.blog.com.service.dto.responseDto.UserProfileDto;

public interface UserService
{
    UserProfileDto getUserProfile();

    void saveUser(UserDto user);
}
