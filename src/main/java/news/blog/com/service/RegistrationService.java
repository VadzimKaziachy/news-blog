package news.blog.com.service;

import news.blog.com.service.dto.UserDto;
import news.blog.com.service.dto.response.UserProfileResponseDto;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface RegistrationService
{
    UserProfileResponseDto registrationUser(UserDto userDto);

    OAuth2AccessToken getAccessToken(String userName, String clientId);
}
