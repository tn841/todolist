package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String SELECT_ALL = "SELECT * FROM todo ORDER BY date";	//오름차순 정렬
	static final String SELECT_BY_COMPLETED = "SELECT * FROM todo WHERE completed=:completed ORDER BY date";
	static final String DELETE_BY_ID ="DELETE FROM todo WHERE id= :id";
	static final String UPDATE_BY_ID = "UPDATE todo SET todo=:todo, completed=:completed WHERE id=:id";
	static final String DELETE_BY_COMPLETED = "DELETE FROM todo WHERE completed=:completed";
}
