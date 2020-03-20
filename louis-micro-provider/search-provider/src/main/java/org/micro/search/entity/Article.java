package org.micro.search.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author John·Louis
 *  created at 2019/12/26
 * description:
 */
@Data
@Builder
@AllArgsConstructor
@Document(indexName = "article")
public class Article {
    private long id;
    private String content;
    private String title;
}
