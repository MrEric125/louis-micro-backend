package org.micro.security.devdata;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.micro.security.entity.ClientDetailEntity;
import org.micro.security.entity.GrandType;
import org.micro.security.entity.intermediate.GrandTypeAndClientDetail;
import org.micro.security.repository.ClientDetailsRepository;
import org.micro.security.repository.GrandTypeClientDetailsRepository;
import org.micro.security.repository.GrandTypeRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author louis
 * <p>
 * Date: 2019/12/4
 * Description:
 */
@Slf4j
@Configuration
public class ClientDetailsCreateToJdbc implements InitializingBean {


    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    ClientDetailsRepository clientDetailsRepository;

    @Autowired
    GrandTypeRepository grandTypeRepository;

    @Autowired
    GrandTypeClientDetailsRepository grandTypeClientDetailsRepository;


    @Override
    public void afterPropertiesSet() throws Exception {

        if (StringUtils.isNotEmpty(profile) && StringUtils.equals("dev", profile)) {
            log.info("dev client detail 自定义数据初始化>>>>>");
            List<ClientDetailEntity> clientDetails = DevDataAutoCreate.loadClientDetails();
            List<ClientDetailEntity> savedClientDetails=clientDetails.stream().map(clientDetail -> {
                Optional<ClientDetailEntity> opClientDetails = clientDetailsRepository.findByClientId(clientDetail.getClientId());
                return opClientDetails.orElseGet(() -> clientDetailsRepository.save(clientDetail));
            }).collect(Collectors.toList());

            List<GrandType> grandTypes = DevDataAutoCreate.LoadGrandTypes();

            List<GrandType> savedGrandType = grandTypes.stream().map(grandType -> {
                Optional<GrandType> opGrandType = grandTypeRepository.findByValue(grandType.getValue());
                return opGrandType.orElseGet(() -> grandTypeRepository.save(grandType));
            }).collect(Collectors.toList());

//        默认每个clientDetails包含所有的grandType
            savedClientDetails.forEach(clientDetail -> {
                savedGrandType.forEach(grandType -> {
                    Optional<GrandTypeAndClientDetail> gtcd = grandTypeClientDetailsRepository
                            .findByClientDetailIdAndGrandTypeId(clientDetail.getClientId(), grandType.getValue());
                    gtcd.orElseGet(() -> grandTypeClientDetailsRepository.save(
                            GrandTypeAndClientDetail.builder()
                                    .clientDetailId(clientDetail.getClientId())
                                    .grandTypeId(grandType.getValue())
                                    .build())
                    );

                });
            });
        }




    }
}
