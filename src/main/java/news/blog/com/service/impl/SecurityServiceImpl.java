package news.blog.com.service.impl;

import lombok.AllArgsConstructor;
import news.blog.com.model.UserEntity;
import news.blog.com.repository.UserRepository;
import news.blog.com.service.SecurityService;
import news.blog.com.service.dto.BlogUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService
{

    private final UserRepository userRepository;

    @Override
    public UserEntity getUserEntity()
    {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null)
        {
            if (authentication.getPrincipal() instanceof BlogUserDetails)
            {
                BlogUserDetails springSecurityUser = (BlogUserDetails) authentication.getPrincipal();
                return userRepository.findByLogin(springSecurityUser.getUser().getLogin());
            }
        }
        return null;
    }
}
