package org.micro.backend.base.entity.todo;

import java.util.Arrays;

/**
 * @author John·Louis
 * @date created on 2020/3/8
 * description:
 */
public enum TodoStatus {

    COMPLETE(0),ACTIVE(1);

    private int statusCode;

    public int statusCode(){
        return statusCode;
    }

    TodoStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public static TodoStatus getEnum(int statusCode) {
       return Arrays.stream(TodoStatus.values())
               .filter(status -> status.statusCode() == statusCode)
               .findFirst()
               .orElse(null);
    }


}
