package org.micro.security.devdata;

import com.google.common.collect.Lists;
import org.micro.security.SysRole;
import org.micro.security.entity.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * @author John·Louis
 * @date create in 2019/11/30
 * description:
 */
public class DevDataAutoCreate {

    public static List<SysRole> loadRole() {
        SysRole role = SysRole.builder().roleName("admin").build();
        SysRole role2 = SysRole.builder().roleName("user").build();
        return Lists.newArrayList(role, role2);


    }
    public static List<SysUser> loadUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        SysUser user1 = SysUser.builder().username("user").loginPwd(passwordEncoder.encode("123456")).build();
        SysUser user2 = SysUser.builder().username("admin").loginPwd(passwordEncoder.encode("123456")).build();
        SysUser user3 = SysUser.builder().username("visitor").loginPwd(passwordEncoder.encode("123456")).build();
        return Lists.newArrayList(user1, user2, user3);

    }
    public static List<UserAction> loadUserAction() {
        UserAction userAction = UserAction.builder().actionCode("select").menuId(1L).url("/**").user(1L).build();
        UserAction userAction1 = UserAction.builder().actionCode("select").menuId(1L).url("/**").user(2L).build();
        UserAction userAction2 = UserAction.builder().actionCode("select").menuId(1L).url("/**").user(3L).build();
        return Lists.newArrayList(userAction, userAction1, userAction2);
    }
    public  static List<RolePermissionEntity> loadrolePermissionEntity() {
        return null;
    }
    public static List<GrandType> LoadGrandTypes(){
        GrandType password = GrandType.builder().value("password").build();
        GrandType authorizationCode = GrandType.builder().value("authorization_code").build();
        GrandType refreshToken = GrandType.builder().value("refresh_token").build();
        return Lists.newArrayList(password, authorizationCode, refreshToken);
    }
//    public static List<ClientDetailEntity> loadClientDetails() {
//        ClientDetailEntity clientDetail = ClientDetailEntity.builder()
//                .clientId("louis")
//                .clientSecret("123456")
//                .scope("webclient")
//                .redirectUrl("http://baidu.com")
//                .build();
//        ClientDetailEntity clientDetail2 = ClientDetailEntity.builder()
//                .clientId("client")
//                .clientSecret("123456")
//                .scope("webclient")
//                .redirectUrl("http://baidu.com")
//                .build();
//        return Lists.newArrayList(clientDetail, clientDetail2);
//    }
    public List<ScopeEntity> loadScope() {
        ScopeEntity scopeEntity = ScopeEntity.builder().scopeValue("webclient").build();
        return Lists.newArrayList(scopeEntity);
    }


}
