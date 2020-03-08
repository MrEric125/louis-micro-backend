package org.micro.web.common;

import org.micro.base.Searchable;
import org.micro.base.entity.BaseEntity;

import java.io.Serializable;

/**
 * @author John·Louis
 * @date created on 2020/3/8
 * description:
 */
public interface IBaseController<T extends BaseEntity<ID>, ID extends Serializable> {

}
