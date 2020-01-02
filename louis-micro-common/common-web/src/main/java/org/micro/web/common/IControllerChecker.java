package org.micro.web.common;

import org.micro.base.KeyValue;
import org.springframework.stereotype.Component;

/**
 * @author louis
 * <p>
 * Date: 2020/1/2
 * Description:
 * 定义所有的controller,的执行流程：
 *
 * {{@link #checkOperationValid()}}： 用于校验接口是否可用，在某些场景下
 * （业务高峰期，），某些接口可能会对整个性能有很大影响，但是这些接口又不是核心的
 * 所以我们可以通过配置项，将这个接口设置为暂时不可用。
 *
 * {@link #checkHasPermission()} : 用于校验调用者是否有权限调用这个接口
 *
 * {@link #preExecute()} 业务逻辑前置处理器： 在某些情况下，我们可能需要对
 * 参数进行校验，或者设置某些属性，或者其他相关操作等，都可以在这个里面操作
 *
 * {@link #execute()}: 真正执行业务逻辑的地方
 *
 * {@link #postExecute()}: 后置处理器
 *
 *
 */
@Component
public interface IControllerChecker {

    /**
     * 校验一个接口是否可用:
     * 可以配置成粗粒度(class级别)，也可以配置成细粒度(method级别)，高度可配置化
     */
    KeyValue<Boolean,String> checkOperationValid();

    /**
     * 校验调用者是否有权限调用这个接口
     */
    void checkHasPermission();

    /**
     * 前置处理器
     */
    void preExecute();

    /**
     * 业务逻辑处理器
     */
    void execute();

    /**
     * 后置处理器
     */
    void postExecute();

}
