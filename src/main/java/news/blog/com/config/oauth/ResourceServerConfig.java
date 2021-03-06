package news.blog.com.config.oauth;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@AllArgsConstructor
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    private final Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);

    private final TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http)
    {
        try
        {
            http
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/").permitAll()
                    .antMatchers("/users/registration").permitAll()
                    .antMatchers("/auth/**").permitAll()
                    .anyRequest().hasAnyRole("USER", "ADMIN");
        }
        catch (Exception e)
        {
            logger.error("Resource configuration was failed", e);
        }
    }
}
