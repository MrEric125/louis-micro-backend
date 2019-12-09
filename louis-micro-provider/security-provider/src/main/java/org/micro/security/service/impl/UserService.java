package org.micro.security.service.impl;

import org.micro.security.entity.SysUser;
import org.micro.security.entity.UserAction;
import org.micro.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/11/19
 * Description:
 */
@Service
public class UserService {

    @Autowired
    private UserActionService userActionService;

    @Autowired
    private UserRepository userRepository;


    private Collection<GrantedAuthority> loadUserAuthorities(Long userId) {
        List<UserAction> ownAuthList = userActionService.getOwnActionListByUserId(userId);
        return ownAuthList.stream()
                .map(x -> new SimpleGrantedAuthority(x.getUrl()))
                .collect(Collectors.toList());
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> grantedAuthorities;
        SysUser user=userRepository.findByUsername(username).orElseThrow(()-> new BadCredentialsException("用户名不存在或者密码错误"));
        grantedAuthorities = loadUserAuthorities(user.getId());
        return new User(user.getUsername(), user.getLoginPwd(), grantedAuthorities);
    }

}
