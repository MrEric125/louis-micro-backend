package org.micro.provider.repository;

import org.micro.provider.entity.OrderEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author John·Louis
 * @date create in 2019/12/23
 * description:
 */
public interface OrderEsRepository extends ElasticsearchRepository<OrderEs, Long> {



}
