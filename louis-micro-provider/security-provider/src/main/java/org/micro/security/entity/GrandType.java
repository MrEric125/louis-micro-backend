package org.micro.security.entity;

import lombok.*;
import org.micro.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author louis
 * <p>
 * Date: 2019/12/4
 * Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_grant_type")
public class GrandType extends BaseEntity<Long> {

    @Column(name = "value", nullable = false, unique = true)
    private String value;


}
