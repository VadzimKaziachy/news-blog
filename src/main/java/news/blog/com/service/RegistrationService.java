package news.blog.com.service;

import news.blog.com.service.dto.UserDto;
import news.blog.com.service.dto.responseDto.UserProfileDto;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface RegistrationService
{
    UserProfileDto registrationUser(UserDto userDto);

    OAuth2AccessToken getAccessToken(String userName, String clientId);
}
