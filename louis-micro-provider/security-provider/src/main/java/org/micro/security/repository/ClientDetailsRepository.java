package org.micro.security.repository;

import com.louis.core.repository.BaseRepository;
import org.micro.security.entity.ClientDetail;
import org.springframework.stereotype.Repository;

/**
 * @author louis
 * <p>
 * Date: 2019/12/4
 * Description:
 */
@Repository
public interface ClientDetailsRepository extends BaseRepository<ClientDetail, Long> {

}
