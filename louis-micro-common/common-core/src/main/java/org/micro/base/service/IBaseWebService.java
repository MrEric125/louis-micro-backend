package org.micro.base.service;

import org.micro.base.Searchable;
import org.micro.base.entity.BaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author John·Louis
 * @date created on 2020/3/8
 * description:
 * 抽象的一套公共service接口 默认实现类为 {@link org.micro.base.service.impl.BaseWebServiceImpl}
 */
public interface IBaseWebService<T extends BaseEntity<ID>, ID extends Serializable> extends IBaseService {

    /**
     * 保存功能
     * @param t 实体
     * @return 当前实体
     */
    T save(T t);
    /**
     * 批量保存
     * @param entities 批量实体
     * @return 保存成功的数量
     */
    int saveAll(List<T> entities);

    /**
     * 通过Id 查询
     * @param id 查询id
     * @return 返回实体
     */
    Optional<T> findById(ID id);

    /**
     * 分页查询
     * @param pageable 分页信息
     * @return 查询到的分页信息
     */
    Page<T> findByPage(Pageable pageable);

    Page<T> findAll(Searchable searchable);

    /**
     * 通过实体删除
     * @param t 需要删除的实体
     */
    void delete(T t);

    /**
     * 通过id删除
     * @param id 删除id
     */
    void delete(ID id);

    /**
     * 批量删除
     * @param entities 删除实体
     */
    void delete(List<T> entities);

    /**
     * 通过查询条件查询数量
     * @param example 查询条件
     * @return 数量
     */
    long count(Example<T> example);










}
