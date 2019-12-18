package org.micro.security.repository;

import org.micro.base.repository.BaseRepository;
import org.micro.security.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author John·Louis
 * @date create in 2019/11/30
 * description:
 */
@Repository
public interface RoleRepository extends BaseRepository<SysRole, Long> {

    Optional<SysRole> getByRoleName(String roleName);


}
