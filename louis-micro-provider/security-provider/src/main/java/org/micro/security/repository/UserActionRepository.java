package org.micro.security.repository;

import com.louis.core.repository.BaseRepository;
import org.micro.security.entity.UserAction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author John·Louis
 * @date create in 2019/12/1
 * description:
 */
@Repository
public interface UserActionRepository extends BaseRepository<UserAction, Long> {

    List<UserAction> findByUser(Long userId);
}
