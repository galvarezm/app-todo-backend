package cl.apiux.springboot.app.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.apiux.springboot.app.todo.models.Todo;
import cl.apiux.springboot.app.todo.services.ITodoService;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
	
	@Autowired
	private ITodoService todoService;

	@CrossOrigin
	@GetMapping("/list")
	public ResponseEntity<List<Todo>> listTodo(){
		List<Todo> todoList = todoService.findAll();
		if (todoList == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<List<Todo>>(todoList, HttpStatus.OK);
		} 
	}
	
	@CrossOrigin
	@GetMapping("/detail/{id}")
	public ResponseEntity<Todo> detailTodo(@PathVariable Long id) {
		Todo todoTask = todoService.findById(id);
		if (todoTask == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Todo>(todoTask, HttpStatus.OK);
		}
	}

	@CrossOrigin
	@PostMapping(path = "add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Todo> addTodo(@RequestBody Todo todo) {
		// Validar campos requeridos
		if (todo.getTitle().isEmpty() || todo.getDescription().isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Todo todoTask = todoService.addTodo(todo);
	    if (todoTask == null) {
	    	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    } else {
	        return new ResponseEntity<Todo>(todoTask, HttpStatus.OK);
	    }
	}

	@CrossOrigin
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteTodoById(@PathVariable Long id) {
		try {
			todoService.deleteTodoById(id);
			return new ResponseEntity<>(null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@PutMapping(path = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
		// Validar campos requeridos
		if (todo.getTitle().isEmpty() || todo.getDescription().isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Todo todoTask = todoService.updateTodo(todo);
	    if (todoTask == null) {
	    	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    } else {
	        return new ResponseEntity<Todo>(todoTask, HttpStatus.OK);
	    }
	}

}
