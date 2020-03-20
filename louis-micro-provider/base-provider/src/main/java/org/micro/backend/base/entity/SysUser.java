package org.micro.backend.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.micro.base.entity.BaseEntity;
import org.micro.base.entity.LogicDeleteable;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "sys_user")
@ApiModel(value="org.micro.backend.base.entity.SysUser")
@JsonIgnoreProperties(ignoreUnknown = true)

public class SysUser extends BaseEntity<Long> implements LogicDeleteable {


    @Column(name = "login_pwd")
    @ApiModelProperty(value="null")
    private String loginPwd;

    @Column(name = "user_status")
    @ApiModelProperty(value="null")
    private String userStatus;

    @Column(name = "username")
    @ApiModelProperty(value="null")
    private String username;

    @Column(name = "deleted")
    private boolean deleted;

    @Override
    public Boolean getDeleted() {
        return this.deleted;
    }

    @Override
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;

    }

    @Override
    public void markDeleted() {
        this.deleted = true;

    }
}