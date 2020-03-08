package org.micro.base.service.impl;

import org.micro.base.Searchable;
import org.micro.base.exception.UnAchievedException;
import org.micro.base.repository.BaseRepository;
import org.micro.base.service.IBaseWebService;
import org.micro.base.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author John·Louis
 * @date created on 2020/3/8
 * description:
 */
@Transactional
public abstract class BaseWebService<T extends BaseEntity<ID>,ID extends Serializable> implements IBaseWebService<T,ID> {

    protected BaseRepository<T, ID> baseRepository;

    @Autowired
    public  void setBaseRepository(BaseRepository<T, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    protected <R extends BaseRepository<T,ID>> R getRepository(Class<R> rClass) {
        return rClass.cast(baseRepository);
    }

    protected  abstract <R extends BaseRepository<T, ID>> R getRepository();

    @Override
    public T save(T t) {
        return baseRepository.save(t);
    }

    @Override
    public int saveAll(List<T> entities) {
        return baseRepository.saveAll(entities).size();
    }

    @Override
    public Optional<T> findById(ID id) {
        return baseRepository.findById(id);
    }

    @Override
    public Page<T> findByPage(Pageable pageable) {
        return baseRepository.findAll(pageable);
    }


    @Override
    public Page<T> findAll(Searchable searchable) {
        throw new UnAchievedException("此方法还未实现");
    }

    @Override
    public void delete(T t) {
        baseRepository.delete(t);

    }

    @Override
    public void delete(ID id) {
        baseRepository.deleteById(id);
    }

    @Override
    public void delete(List<T> entities) {
        baseRepository.deleteInBatch(entities);

    }

    @Override
    public long count(Example<T> example) {
        return baseRepository.count(example);
    }
}
