package org.micro.security.config;

import org.micro.security.handler.*;
import org.micro.security.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author louis
 * <p>
 * Date: 2019/11/29
 * Description:
 * 安全服务器配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 未登陆时返回 JSON 格式的数据给前端（否则为 html）
     */
    @Autowired
    MyAuthenticationEntryPoint authenticationEntryPoint;

    /*
    *登录成功返回的 JSON 格式数据给前端（否则为 html）
     */
    @Autowired
    MyAuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 登录失败返回的 JSON 格式数据给前端（否则为 html）
     */
    @Autowired
    MyAuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
     */
    @Autowired
    MyLogoutSuccessHandler logoutSuccessHandler;

    /**
     * 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
     */
    @Autowired
    MyAccessDeniedHandler accessDeniedHandler;

    /**
     *  自定义安全认证
     */
    @Autowired
    MyAuthenticationProvider provider;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     * 安全拦截机制
     * @param
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)

                .and()
//                不使用session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 所有/权限认证 的所有请求 都放行
                .antMatchers("/user/**").permitAll()
                .anyRequest()
                .authenticated()// 其他 url 需要身份认证

                .and()
                .formLogin()  // 开启登录
                .loginProcessingUrl("/user/login")
                .usernameParameter("username")//请求验证参数
                .passwordParameter("password")//请求验证参数
                .successHandler(authenticationSuccessHandler) // 登录成功
                .failureHandler(authenticationFailureHandler) // 登录失败
                .permitAll()

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//  http.authorizeRequests().anyRequest().access("@permissionService.hasPermission(authentication,request)");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
        auth.authenticationProvider(provider);
    }
}
