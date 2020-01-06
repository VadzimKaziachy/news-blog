package news.blog.com.aspect;

import news.blog.com.annotation.ValidateEmail;

import news.blog.com.exception.NotValidDataException;
import news.blog.com.service.dto.UserDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class EmailAspect
{
    public static final String USER_DTO_EMAIL = "UserDto";

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Around(value = "execution(public * *(..)) && @annotation(validateEmail))")
    public Object before(ProceedingJoinPoint joinPoint, ValidateEmail validateEmail) throws Throwable
    {
        String key = validateEmail.key();
        switch (key)
        {
            case USER_DTO_EMAIL:
                {
                    Object[] args = joinPoint.getArgs();
                    if (args.length > 0)
                    {
                        UserDto user = (UserDto) args[0];
                        if(user.getEmail() != null && !isValidEmail(user.getEmail()))
                        {
                            throw new NotValidDataException("Email is not valid");
                        };
                    }
                }
        }
        return joinPoint.proceed();
    }

    private boolean isValidEmail(String email)
    {
        return email.matches(EMAIL_REGEX);
    }
}
