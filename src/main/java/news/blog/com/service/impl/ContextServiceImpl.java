package news.blog.com.service.impl;

import lombok.AllArgsConstructor;
import news.blog.com.exception.ForbiddenException;
import news.blog.com.service.ContextService;
import news.blog.com.service.CurrentUserProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("contextService")
public class ContextServiceImpl implements ContextService
{
    @Resource(name = "userService")
    private CurrentUserProvider currentUserProvider;

    @Override
    public boolean assertCurrentUserHasAdminRole()
    {
        if(!currentUserProvider.isCurrentUserHasAdminRole())
        {
            throw new ForbiddenException("Current user can't perform administrative actions globally");
        }
        return true;
    }
}
