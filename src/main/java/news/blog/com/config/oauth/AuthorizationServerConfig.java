package news.blog.com.config.oauth;

import javax.sql.DataSource;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter
{
    private final Logger logger = LoggerFactory.getLogger(AuthorizationServerConfig.class);

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;


    @Bean
    @Primary
    public JdbcTokenStore tokenStore()
    {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices()
    {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices()
    {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setRefreshTokenValiditySeconds(0);
        tokenServices.setAccessTokenValiditySeconds(2 * 60 * 60);
        return tokenServices;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer)
    {
        oauthServer
                .passwordEncoder(passwordEncoder)
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
    {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .approvalStoreDisabled();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
    {
        try
        {
            clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
        }
        catch (Exception e)
        {
            logger.error("Password encoder was not set", e);
        }
    }
}

