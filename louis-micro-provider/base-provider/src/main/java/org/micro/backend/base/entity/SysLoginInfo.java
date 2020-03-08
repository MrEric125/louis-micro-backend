package org.micro.backend.base.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.persistence.*;

import lombok.*;
import org.micro.base.entity.BaseEntity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "sys_login_info")
@ApiModel(value="org.micro.backend.entity.SysLoginInfo")
public class SysLoginInfo extends BaseEntity<Long> {


    @Column(name = "ip")
    @ApiModelProperty(value="ip地址")
    private String ip;

    @Column(name = "last_login")
    @ApiModelProperty(value="最近登录时间")
    private Date lastLogin;

    @Column(name = "user_id")
    @ApiModelProperty(value="用户id")
    private Long userId;

    @Column(name = "username")
    @ApiModelProperty(value="用户名")
    private String username;

    @Column(name = "os")
    @ApiModelProperty(value="操作系统")
    private String os;

    @Column(name = "request_url")
    @ApiModelProperty(value="请求url")
    private String requestUrl;

    @Column(name = "last_login_location")
    @ApiModelProperty(value="用户归属地")
    private String lastLoginLocation;

    @Column(name = "browser")
    @ApiModelProperty(value="用户浏览器")
    private String browser;
}