package org.micro.backend.base.seervice;

import org.micro.backend.base.entity.todo.TodoList;
import org.micro.backend.base.repository.TodoListRepository;
import org.micro.base.service.impl.BaseWebService;
import org.springframework.stereotype.Service;

/**
 * @author John·Louis
 * @date created on 2020/3/9
 * description:
 */
@Service
public class TodoListService extends BaseWebService<TodoList,Long> {
    @Override
    protected TodoListRepository getRepository() {
        return getRepository(TodoListRepository.class);
    }

}
