package org.micro.security.entity;

import com.louis.core.entity.AbstractAuditable;
import com.louis.core.entity.BaseEntity;
import lombok.*;

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
@Table(name = "grant_type")
public class GrandType extends BaseEntity<Long> {

    @Column(name = "value", nullable = false)
    private String value;


}
