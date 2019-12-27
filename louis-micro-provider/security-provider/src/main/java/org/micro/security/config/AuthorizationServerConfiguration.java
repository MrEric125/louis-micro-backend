package org.micro.security.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/12/5
 * Description:
 * 授权服务配置
 */

@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter implements InitializingBean {


    @Value("${spring.profiles.active}")
    private String profile;



    @Autowired
    DataSource dataSource;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 认证管理器
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 授权码服务
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }


    /**
     * 令牌存储方式，使用内从方式存储令牌
     * todo 后期需要统一修改为基于数据库
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        // 基于 JDBC 实现，令牌保存到数据
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ClientDetailsService jdbcClientDetails() {
        // 基于 JDBC 实现，需要事先在数据库配置客户端信息
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 令牌配置
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setClientDetailsService(jdbcClientDetails());
        tokenServices.setSupportRefreshToken(true); //是否刷新令牌
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setAccessTokenValiditySeconds(7200);
//        属性令牌时间
        tokenServices.setRefreshTokenValiditySeconds(259200);
        return tokenServices;
    }


    /**
     * 配置客户端详情信息服务，使用jdbc的方式，直接从数据库读取数据
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 读取客户端配置
        clients.withClientDetails(jdbcClientDetails());
    }

    /**
     * 令牌访问端点访问配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
//                授权码模式管理服务
                .authorizationCodeServices(authorizationCodeServices())
//                令牌管理服务
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore())
        ;
    }

    /**
     * 令牌访问端点安全策略
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        /oauth/token_key的公开
        security.tokenKeyAccess("permitAll()")
//                /oauth/check_token
                .checkTokenAccess("permitAll()")
//                允许表单认证（申请令牌）
                .allowFormAuthenticationForClients();
    }

    /**
     *
     * dev环境使用 方便在不同环境有相同的数据，保证环境的一致性
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isNotEmpty(profile) && StringUtils.equals("dev", profile)) {
            log.info("dev client detail 数据初始化>>>>>");
            JdbcClientDetailsService jdbcClientDetailsService = (JdbcClientDetailsService) jdbcClientDetails();
            List<ClientDetails> clientDetails = jdbcClientDetailsService.listClientDetails();

            clientDetails.stream()
                    .filter(x -> StringUtils.equals("client", x.getClientId()))
                    .findAny()
                    .orElseGet(()->{
                        BaseClientDetails baseClientDetails = new BaseClientDetails();
                        baseClientDetails.setClientId("client");
                        baseClientDetails.setClientSecret(passwordEncoder.encode("client"));
                        baseClientDetails.setAuthorizedGrantTypes(Lists.newArrayList("refresh_token", "password", "client_credentials", "authorization_code","implicit","refresh_token"));
                        baseClientDetails.setAccessTokenValiditySeconds(30000);
                        baseClientDetails.setRefreshTokenValiditySeconds(30000);
                        baseClientDetails.setRegisteredRedirectUri(Sets.newHashSet("http://www.baidu.com"));
                        baseClientDetails.setScope(Lists.newArrayList("webclient"));
                        jdbcClientDetailsService.addClientDetails(baseClientDetails);
                        return baseClientDetails;
                    });
        }
    }
}
