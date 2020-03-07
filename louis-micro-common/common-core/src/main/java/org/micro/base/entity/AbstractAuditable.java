package org.micro.base.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 可审计的，创建统一的时间和人物管理关系
 *
 * @author John·Louis
 * @date created on 2020/3/8
 * description:
 * 可以通过 {@link AuditorAware} 来返回当前操作人
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = true)
public class AbstractAuditable<ID extends Serializable> extends BaseEntity<ID> {
    private static final long serialVersionUID = -7845283411758422624L;

    @NotNull
    @Size(max = 50)
    @Column(name = "created_by", length = 50, updatable = false, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", updatable = false, nullable = false)
    private Date createdDate;

    @Size(max = 50)
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;




}
