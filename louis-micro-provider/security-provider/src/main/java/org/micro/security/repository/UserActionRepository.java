package org.micro.security.repository;

import org.micro.base.repository.BaseRepository;
import org.micro.security.entity.UserAction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author John·Louis
 *  created at 2019/12/1
 * description:
 */
@Repository
public interface UserActionRepository extends BaseRepository<UserAction, Long> {

    List<UserAction> findByUser(Long userId);
}
