package org.micro.web.common;

import org.micro.base.KeyValue;

/**
 * @author louis
 * <p>
 * Date: 2020/1/2
 * Description:
 * 会和AbstractController配合使用，
 */
public abstract class AbstractControllerChecker implements IControllerChecker {
    /**
     * 默认接口可用
     * @return
     */
    @Override
    public KeyValue<Boolean, String> checkOperationValid() {
        Boolean key = Boolean.TRUE;
        return KeyValue.<Boolean, String>builder().key(key).build();
    }
}
