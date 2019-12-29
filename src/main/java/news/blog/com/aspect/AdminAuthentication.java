package news.blog.com.aspect;

import lombok.RequiredArgsConstructor;

import news.blog.com.service.ContextService;
import news.blog.com.annotation.WithAdminAuthentication;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Aspect
@Configuration
@RequiredArgsConstructor
public class AdminAuthentication
{
    @Resource(name = "contextService")
    private ContextService contextService;


    @Before(value = "@annotation(withAdminAuthentication)")
    public void before(WithAdminAuthentication withAdminAuthentication)
    {
        isAdminRole();
    }

    private void isAdminRole()
    {
        contextService.assertCurrentUserHasAdminRole();
    }
}
