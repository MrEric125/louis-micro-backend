package org.micro.web.common;

import org.micro.base.entity.BaseEntity;
import org.micro.base.service.impl.BaseWebService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author louis
 * <p>
 * Date: 2020/1/2
 * Description:
 */

public abstract class BaseWebController<T extends BaseEntity<ID>, ID extends Serializable> implements IBaseController<T, ID> {

    protected BaseWebService baseWebService;


    @Autowired
    private IControllerChecker checker;

    /**
     * 设置基础service
     *
     */
    @Autowired
    public void setBaseService(BaseWebService<T, ID> baseService) {
        this.baseWebService = baseService;
    }
    protected <S extends BaseWebService<T,ID>> S getService(Class<S> sClass) {
        return sClass.cast(baseWebService);
    }

    protected  abstract <R extends BaseWebService<T, ID>> R getService();


}
