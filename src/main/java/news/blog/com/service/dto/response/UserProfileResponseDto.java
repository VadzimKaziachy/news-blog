package news.blog.com.service.dto.response;

import lombok.Data;
import news.blog.com.model.type.UserRole;

@Data
public class UserProfileResponseDto
{
    private String login;
    private UserRole role;
    private String lastName;
    private String firstName;
    private String accessToken;
}
