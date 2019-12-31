package org.micro.search.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;


/**
 * @author John·Louis
 * @date create in 2019/12/23
 * description:
 */
@Data
@Document(indexName = "order")
public class OrderEs {

    private Long id;

    private String orderId;

    private String orderType;

}
