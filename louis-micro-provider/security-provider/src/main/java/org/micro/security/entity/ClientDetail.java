package org.micro.security.entity;

import com.louis.core.entity.AbstractAuditable;
import com.louis.core.entity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
@Table(name = "client_detail")
public class ClientDetail extends AbstractAuditable<Long> {
    private static final long serialVersionUID = 7100834559771058030L;
    @NonNull
    @NotNull
    @Column(name = "client_id", unique = true, nullable = false, length = 200)
    private String clientId;

    @NonNull
    @NotNull
    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Column(name = "scope", nullable = false)
    private String scope;

    @Column(name = "authorized_grant_types")
    private String authorizedGrandTypes;

    @Column(name = "web_server_redirect_uri", nullable = false)
    private String redirectUrl;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    private String accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private String refreshTokenValidity;

    @Column(name = "additional_information")
    private String additionalInformation;

    @Column(name = "auto_approve")
    private String autoApprove;



}
