package cl.apiux.springboot.app.todo.services;

import java.util.List;

import cl.apiux.springboot.app.todo.models.Todo;

public interface ITodoService {
	
	public List<Todo> findAll();
	public Todo findById(Long id);
	public Todo addTodo(Todo todo);
	public void deleteTodoById(Long id);
	public Todo updateTodo(Todo todo);

}
