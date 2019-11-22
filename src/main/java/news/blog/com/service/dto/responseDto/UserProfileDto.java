package news.blog.com.service.dto.responseDto;

import lombok.Data;
import news.blog.com.model.type.UserRole;

@Data
public class UserProfileDto
{
    private String login;
    private UserRole role;
    private String lastName;
    private String firstName;
    private String accessToken;
}
