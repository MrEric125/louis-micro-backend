package org.micro.security.repository;

import com.louis.core.repository.BaseRepository;
import org.micro.security.entity.ClientDetailEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2019/12/4
 * Description:
 */
@Repository
public interface ClientDetailsRepository extends BaseRepository<ClientDetailEntity, Long> {


    Optional<ClientDetailEntity> findByClientId(String clientId);
}
