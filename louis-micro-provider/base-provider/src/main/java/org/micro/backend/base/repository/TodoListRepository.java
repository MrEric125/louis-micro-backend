package org.micro.backend.base.repository;

import org.micro.backend.base.entity.todo.TodoList;
import org.micro.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author John·Louis
 * @date created on 2020/3/9
 * description:
 */
@Repository
public interface TodoListRepository extends BaseRepository<TodoList, Long> {

}
