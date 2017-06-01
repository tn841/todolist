package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String SELECT_ALL = "SELECT * FROM todo";
	static final String SELECT_BY_COMPLETED = "SELECT * FROM todo WHERE completed=:completed";
	static final String DELETE_BY_ID ="DELETE FROM todo WHERE id= :id";
	static final String UPDATE_BY_ID = "UPDATE todo SET todo=:todo, completed=:completed WHERE id=:id";
	static final String DELETE_BY_COMPLETED = "DELETE FROM todo WHERE completed=:completed";
	static final String SELECT_BY_FILTER = "SELECT * FROM todo WHERE completed=:completed";
}
