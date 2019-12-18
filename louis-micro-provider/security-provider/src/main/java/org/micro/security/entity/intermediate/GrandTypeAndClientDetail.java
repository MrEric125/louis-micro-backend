//package org.micro.security.entity.intermediate;
//
//import lombok.*;
//import org.micro.security.entity.AbstractAuditable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//
///**
// * @author louis
// * <p>
// * Date: 2019/12/5
// * Description:
// */
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//@Entity
//@Table(name = "client_detail_grant_type_multi")
//public class GrandTypeAndClientDetail extends AbstractAuditable<Long> {
//
//    @NonNull
//    @NotNull
//    @Column(name = "client_id",  nullable = false, length = 200)
//    private String clientDetailId;
//
//    @NonNull
//    @NotNull
//    @Column(name = "grand_type_value", nullable = false, length = 200)
//    private String grandTypeId;
//
//}
