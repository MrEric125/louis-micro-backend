package org.micro.security.repository;

import org.micro.base.repository.BaseRepository;
import org.micro.security.entity.GrandType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2019/12/4
 * Description:
 */
@Repository
public interface GrandTypeRepository extends BaseRepository<GrandType, Long> {

    Optional<GrandType> findByValue(String grandTypeValue);

}
