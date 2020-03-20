package org.micro.backend.base.controller;

import org.micro.backend.base.entity.todo.TodoList;
import org.micro.backend.base.seervice.TodoListService;
import org.micro.web.common.BaseWebController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author John·Louis
 * @date created on 2020/3/9
 * description:
 */
@RestController
@RequestMapping("/todoList")
public class TodoListController extends BaseWebController<TodoList, Long> {

    @Override
    protected TodoListService getService() {
        return getService(TodoListService.class);
    }

}
