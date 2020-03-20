package org.micro.web.common;

import org.micro.common.api.wrapper.*;
import java.util.Objects;

/**
 * @author louis
 * <p>
 * Date: 2019/7/17
 * Description:
 */
public abstract class BaseHandler {
    /**
     *
     * @param result the result
     *
     * @return the wrapper
     */
    protected  Wrapper handleResult(Object result) {
        boolean flag = isFlag(result);
        if (flag) {
            return MapperWrap.wrap(WrapperMassage.SUCCESS_CODE, "success", result);
        } else {
            return MapperWrap.wrap(WrapperMassage.ERROR_CODE, "响应结果为空", result);
        }
    }

    /**
     *
     * @param result   the result
     * @param errorMsg the error msg
     *
     * @return the wrapper
     */
    protected Wrapper handleResult(Object result, String errorMsg) {
        boolean flag = isFlag(result);

        if (flag) {
            return MapperWrap.wrap(WrapperMassage.SUCCESS_CODE, "success", result);
        } else {
            return MapperWrap.wrap(WrapperMassage.ERROR_CODE, errorMsg, result);
        }
    }


    /**
     *
     * @param errorMsg 错误信息

     * @param result 响应结果
     */
    protected  Wrapper handlerNullResult(String errorMsg,Object result) {
        if (errorMsg != null) {
            return MapperWrap.wrap(WrapperMassage.ERROR_CODE,  errorMsg);
        }
        return MapperWrap.wrap(WrapperMassage.SUCCESS_CODE, "success", result);
    }

    /**
     * 如果是返回的分页信息那么一定是成功的，就没有errorMsg
     *注意，在查询的时候需要指定Pageable参数，否则显示的页码信息会有错误
     *
     * @param t 响应类
     */
    protected  Wrapper handlePageAndSortResult(Object t) {
        return PageWrapMapper.handlePageAndSortResult(t);
    }


    protected  Wrapper handlerNullResult() {
        return handlerNullResult(null, null);
    }

    /**
     * 结果可以为空的
     * @param result 返回可以为null 的结果
     */
    protected  Wrapper handlerNullResult(Object result){
        return handlerNullResult(null, result);
    }

    /**
     * 判断对象是否为空
     * @param result 判断的对象
     * @return 是否为null
     */
    private boolean isFlag(Object result) {
        boolean flag;
        if (result instanceof Integer) {
            flag = (Integer) result > 0;
        } else if (result instanceof Boolean) {
            flag = (Boolean) result;
        } else {
            flag = Objects.nonNull(result);
        }
        return flag;
    }
}
