package news.blog.com.service.dto;

import lombok.Data;
import news.blog.com.model.type.UserRole;

import java.io.Serializable;

@Data
public class UserDto implements Serializable
{
    private static final long serialVersionUID = 51355998888834302L;

    private Long id;
    private String login;
    private String password;
    private UserRole role;
    private String lastName;
    private String firstName;
}
