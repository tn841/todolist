package kr.or.connect.todo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	private TodoDao dao;
	
	@Autowired
	public TodoService(TodoDao dao) {
		this.dao = dao;
	}
	
	public Todo insert(Todo todo){
		return dao.insert(todo);
	}
	
	public List<Todo> selectAll(){
		return dao.selectAll();
	}
	
	public List<Todo> selectByCompleted(int completed){
		return dao.selectByCompleted(completed);
	}
	
	public int updateById(Todo todo){
		return dao.updateById(todo);
	}
	
	public int deleteById(int id){
		return dao.deleteById(id);
	}
	
	public int deleteCompleted(){
		return dao.deleteCompleted();
	}
}
