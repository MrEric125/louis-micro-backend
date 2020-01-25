package org.micro.security.repository;

import org.micro.base.repository.BaseRepository;
import org.micro.security.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author John·Louis
 *  create at 2019/11/30
 * description:
 */
@Repository
public interface UserRepository extends BaseRepository<SysUser, Long> {

    Optional<SysUser> findByUsername(String userName);

}
