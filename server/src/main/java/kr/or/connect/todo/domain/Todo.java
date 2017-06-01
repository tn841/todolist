package kr.or.connect.todo.domain;

import java.sql.Timestamp;

public class Todo {
	private int id;
	private String todo;
	private int completed;
	private Timestamp date;
	
	public Todo() {
		super();
	}
	public Todo(String todo, int completed) {
		super();
		this.todo = todo;
		this.completed = completed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "TODO [id=" + id + ", todo=" + todo + ", completed=" + completed + ", date=" + date + "]";
	}
	
}
