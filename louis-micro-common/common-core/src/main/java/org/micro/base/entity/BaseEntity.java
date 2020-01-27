package org.micro.base.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author John·Louis
 *  created at 2019/12/17
 * description:
 */
@Data
public class BaseEntity<ID> implements Serializable {

    private ID id;

    public String getIdToString() {
        return String.valueOf(id);
    }

}
