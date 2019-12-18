package org.micro.security.repository;
import org.micro.base.repository.BaseRepository;
import org.micro.security.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 */
@Repository
public interface UserRoleRepository extends BaseRepository<UserRole, Long> {

    Optional<UserRole> findFirstByUserAndRole(Long userEntity, Long roleEntity);

}
