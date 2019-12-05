package org.micro.security.repository;

import com.louis.core.repository.BaseRepository;
import org.micro.security.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author John·Louis
 * @date create in 2019/11/30
 * description:
 */
@Repository
public interface UserRepository extends BaseRepository<SysUser, Long> {

    Optional<SysUser> findByUsername(String userName);

}
