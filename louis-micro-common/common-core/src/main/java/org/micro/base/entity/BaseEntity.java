package org.micro.base.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;

/**
 * @author John·Louis
 *  created at 2019/12/17
 * description:
 */
@Data
@MappedSuperclass
public class BaseEntity<ID> implements Serializable {

    private static final long serialVersionUID = -2430797350775093998L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    public String getIdToString() {
        return String.valueOf(id);
    }

}
