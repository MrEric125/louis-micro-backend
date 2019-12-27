package org.micro.security.entity;

import lombok.*;
import org.micro.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author louis
 * <p>
 * Date: 2019/12/5
 * Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_scope")
public class ScopeEntity extends BaseEntity<Long> {

    @NonNull
    @NotNull
    @Column(name = "scope", unique = true, nullable = false, length = 200)
    private String scopeValue;
}
