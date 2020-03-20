package org.micro.search.common;

import java.io.Serializable;

/**
 * @author louis
 * <p>
 * Date: 2019/12/30
 * Description:
 */
@FunctionalInterface
public interface SearchStrategy<T extends CommonSearchResponse,R extends CommonSearchRequest> {

    public T execute(R r);

}
