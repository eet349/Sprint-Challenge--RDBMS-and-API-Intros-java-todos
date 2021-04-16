package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "todosService")
public class TodosServiceImpl implements TodosService {
    @Autowired
    private TodosRepository todosrepo;
    @Autowired
    private UserService userService;

    public void markComplete(long todoid) {
        Todos todo = todosrepo.findById(todoid).get();
        todo.setCompleted(true);
    }

    @Transactional
    @Override
    public Todos save(Todos todo)
    {
        Todos newTodo = new Todos();

        newTodo.setCompleted(todo.isCompleted());
        newTodo.setDescription(todo.getDescription());
        newTodo.setUser(userService.findUserById(todo.getUser().getUserid()));

        return todosrepo.save(newTodo);
    }
}
