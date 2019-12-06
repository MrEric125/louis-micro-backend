package org.micro.security.config;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author louis
 * <p>
 * Date: 2019/12/5
 * Description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter implements InitializingBean {


    @Autowired
    DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        // 基于 JDBC 实现，令牌保存到数据
        return new JdbcTokenStore(dataSource);
    }
    @Bean
    public ClientDetailsService jdbcClientDetails() {
        // 基于 JDBC 实现，需要事先在数据库配置客户端信息
        return new JdbcClientDetailsService(dataSource);
    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置令牌
        endpoints.tokenStore(tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 读取客户端配置
        clients.withClientDetails(jdbcClientDetails());
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        JdbcClientDetailsService jdbcClientDetailsService = (JdbcClientDetailsService) jdbcClientDetails();
        List<ClientDetails> clientDetails = jdbcClientDetailsService.listClientDetails();

        clientDetails.stream()
                .filter(x -> StringUtils.equals("client", x.getClientId()))
                .findAny()
                .orElseGet(()->{
                    BaseClientDetails baseClientDetails = new BaseClientDetails();
                    baseClientDetails.setClientId("client");
                    baseClientDetails.setClientSecret("adminClient");
                    baseClientDetails.setAuthorizedGrantTypes(Lists.newArrayList("refresh_token", "password", "client_credentials", "authorization_code"));
                    baseClientDetails.setAccessTokenValiditySeconds(30000);
                    baseClientDetails.setRefreshTokenValiditySeconds(30000);
                    baseClientDetails.setRegisteredRedirectUri(Sets.newHashSet("http://localhost:8981/uas/user"));
                    baseClientDetails.setScope(Lists.newArrayList("webclient"));
                    jdbcClientDetailsService.addClientDetails(baseClientDetails);
                    return baseClientDetails;
                });



    }
}
