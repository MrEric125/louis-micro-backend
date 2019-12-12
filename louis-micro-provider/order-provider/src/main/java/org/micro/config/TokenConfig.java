package org.micro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author louis
 * <p>
 * Date: 2019/12/12
 * Description:
 */
@Configuration
public class TokenConfig {
    @Bean
    public TokenStore tokenStore() {
        //使用内存存储令牌（普通令牌）
        return new InMemoryTokenStore();
    }
}
