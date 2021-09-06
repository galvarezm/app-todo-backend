package cl.apiux.springboot.app.todo.repository;

import org.springframework.data.repository.CrudRepository;

import cl.apiux.springboot.app.todo.models.Todo;

public interface ITodoRepository extends CrudRepository<Todo, Long> {

}
