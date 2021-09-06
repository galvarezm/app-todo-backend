package cl.apiux.springboot.app.todo.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.apiux.springboot.app.todo.models.Todo;
import cl.apiux.springboot.app.todo.repository.ITodoRepository;

@Service
public class TodoServiceImpl implements ITodoService {

	@Autowired
	private ITodoRepository todoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Todo> findAll() {
		return (List<Todo>) todoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Todo findById(Long id) {
		return todoRepository.findById(id).orElse(null);
	}

	@Override
	public Todo addTodo(Todo todo) {
		todo.setCreateAt(new Date());
		return todoRepository.save(todo);
	}

	@Override
	public void deleteTodoById(Long id) {
		todoRepository.deleteById(id);
	}

	@Override
	public Todo updateTodo(Todo todo) {
		return todoRepository.save(todo);
	}

}
