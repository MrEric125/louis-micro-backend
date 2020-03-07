package org.micro.backend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.micro.base.entity.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author John·Louis
 * @date created on 2020/3/8
 * description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "backend_todo_list")
public class TodoList extends AbstractAuditable<Long> {



    private Long userId;

    @NotNull
    @Size(max = 50)
    @Column(name = "todo_title", length = 50, nullable = false)
    private String todoTitle;


    @Column(name = "todo_title")
    private String description;

    /**
     * 描述待办列表的状态
     * 默认值是活跃状态
     */
    @Column(name = "todo_status", nullable = false)
    private int status = TodoStatus.ACTIVE.statusCode();


}
