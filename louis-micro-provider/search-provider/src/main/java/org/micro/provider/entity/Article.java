package org.micro.provider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author John·Louis
 *  create at 2019/12/26
 * description:
 */
@Data
@Builder
@AllArgsConstructor
public class Article {
    private long id;
    private String content;
    private String title;
}
