package org.micro.web.common;

import org.micro.base.entity.BaseEntity;
import org.micro.base.service.impl.BaseWebService;
import org.micro.common.api.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2020/1/2
 * Description:
 */

public abstract class BaseWebController<T extends BaseEntity<ID>, ID extends Serializable> extends BaseHandler implements IBaseController<T, ID> {

    protected BaseWebService baseWebService;

    /**
     * 设置基础service
     */
    @Autowired
    public void setBaseService(BaseWebService<T, ID> baseService) {
        this.baseWebService = baseService;
    }
    protected <S extends BaseWebService<T,ID>> S getService(Class<S> sClass) {
        return sClass.cast(baseWebService);
    }

    protected  abstract <R extends BaseWebService<T, ID>> R getService();


    @RequestMapping("/findById")
    public Wrapper findById(@RequestParam ID id ){
        return getService().findById(id).map(this::handlerNullResult).get();

    }

    @RequestMapping("/findPage")
    public Wrapper findPage(Pageable pageable) {
        Page<T> page = getService().findByPage(pageable);
        return handlePageAndSortResult(page);
    }

    @DeleteMapping("/delete")
    public Wrapper delete(T t) {
         getService().delete(t);
        return handlerNullResult();
    }

    @PostMapping("/save")
    public Wrapper save(T t) {
        T save = getService().save(t);
        return handleResult(save);
    }
}
