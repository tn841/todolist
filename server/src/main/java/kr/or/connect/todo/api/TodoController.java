package kr.or.connect.todo.api;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;
import kr.or.connect.todo.service.TodoService;

@RestController	//REST 컨트롤러임을 표시
@RequestMapping("/api/todos")	// api/todos로 들어오는 모든 요청을 컨트롤한다.
public class TodoController {
	private TodoService service;
	
	@Autowired	//생성자 방식으로 Service를 DI
	public TodoController(TodoService service) {
		this.service = service;
	}
	
	@GetMapping	// get method로 오는 요청 처리 (모든 todo 리스트 반환)
	public Collection<Todo> selectAll(){
		return service.selectAll();
	}
	
	@GetMapping("/filter/{completed}")	// Acitve/Completed 필터가 걸린 요청에 대한 리스트 반환
	public Collection<Todo> selectByCompleted(@PathVariable Integer completed){
		return service.selectByCompleted(completed);
	}
	
	@PostMapping	// Post method로 오는 요청 처리 (todo 하나 생성)
	@ResponseStatus(HttpStatus.CREATED)
	public Todo insert(@RequestBody Todo todo){
		todo.setDate(new Timestamp(new Date().getTime()));
		return service.insert(todo);
	}
	
	
	@PutMapping("/{id}")	//  PUT method로 오는 요청 처리 ({id}에 해당하는 todo 수정)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public int update(@PathVariable Integer id, @RequestBody Todo todo){
		todo.setId(id);
		return service.updateById(todo);
	}
	
	@DeleteMapping("/{id}")	//DELETE method로 오는 요청 처리( {id}에 해당하는 todo 삭제)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public int delete(@PathVariable Integer id){
		return service.deleteById(id);
	}
	
	@DeleteMapping("/completed-item")	// 'completed'된 todo모두 삭제
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public int deleteComplied(){
		return service.deleteCompleted();
	}
	
}
