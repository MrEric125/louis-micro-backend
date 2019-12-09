package org.micro.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * 自定义账号密码验证
 */
@Slf4j
@Configuration
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("使用自定义的账号密码验证>>>");
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();


        UserDetails userInfo = userDetailsService.loadUserByUsername(userName);
        boolean checkpw = BCrypt.checkpw(password, userInfo.getPassword());

        if (!checkpw) {
            log.error("用户名密码不正确，请重新登陆  username:{}, password:{}", userName, password);
            throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
        }

        return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
