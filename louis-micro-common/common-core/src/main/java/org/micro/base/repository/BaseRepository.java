package org.micro.base.repository;

import org.micro.base.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author John·Louis
 * @date create in 2019/12/17
 * description:
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity<ID>,ID extends Serializable> extends JpaRepository<T,ID> {
}
