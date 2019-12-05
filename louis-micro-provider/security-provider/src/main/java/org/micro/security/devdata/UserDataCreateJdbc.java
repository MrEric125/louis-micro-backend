package org.micro.security.devdata;

import org.micro.security.entity.SysRole;
import org.micro.security.entity.SysUser;
import org.micro.security.entity.UserRole;
import org.micro.security.repository.RoleRepository;
import org.micro.security.repository.UserRepository;
import org.micro.security.repository.UserRoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/11/29
 * Description:
 */
@Configuration
public class UserDataCreateJdbc implements InitializingBean {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public void afterPropertiesSet() throws Exception {

//        load Roles
        List<SysRole> defaultRole = DevDataAutoCreate.loadRole();
        List<SysRole> savedRole = defaultRole.stream().
                map(role -> roleRepository.getByRoleName(role.getRoleName())
                        .orElseGet(
                                () -> roleRepository.save(role)
                        )
                ).collect(Collectors.toList());
//        load User
        List<SysUser> savedUser = DevDataAutoCreate.loadUser()
                .stream()
                .map(user -> userRepository.findByUsername(user.getUsername())
                        .orElseGet(
                                () -> userRepository.save(user)
                        )
                ).collect(Collectors.toList());
//        加载同名用户角色对应关系
        savedRole.forEach(role->{
            SysUser userEntity=savedUser.stream()
                    .filter(
                            user -> user.getUsername().equalsIgnoreCase(role.getRoleName())
                    ).findAny().orElseThrow(() -> new RuntimeException("没有找到同名用户或角色"));
            userRoleRepository.findFirstByUserAndRole(userEntity.getId(), role.getId())
                    .orElseGet(
                            () -> userRoleRepository.save(UserRole.builder()
                                    .role(role.getId())
                                    .user(userEntity.getId()).build()
                            )
                    );
        });


    }
}
