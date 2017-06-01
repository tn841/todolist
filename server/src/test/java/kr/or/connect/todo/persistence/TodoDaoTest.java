package kr.or.connect.todo.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.domain.Todo;

@RunWith(SpringRunner.class)	//spring의 ApplicationContext로딩
@SpringBootTest	//spring boot가 자동으로 생성한 application-config를 사용
@Transactional		//transaction을 건다 (디폴트 설정에 의해 다음 class에서 수행한 것들이 전부 rollback 됨)
public class TodoDaoTest {

	@Autowired	//Spring Boot에 의해 @ComponentScan이 이루어졌고,, Context에 Bean으로 등록된 TodoDao가 DI된다.
	private TodoDao dao;
	
	@Test	//JUnit에 의해 테스팅 될 메소드
	public void shouldSelectAll(){
		//given
		
		//when
		List<Todo> allTodos = dao.selectAll();
		
		//then
		assertThat(allTodos.size(), is(6));	//실제값과 예측값을 비교하여 테스팅
	}
	
	@Test
	public void shouldInsert(){
		//given
		Todo todo = new Todo("할일 테스트", 0);
		todo.setDate(new Timestamp(new Date().getTime()));
		
		//when
		Todo insertedTodo = dao.insert(todo);
		
		//then
		assertThat(insertedTodo.getTodo(), is("할일 테스트"));
	}
	
	@Test
	public void shouldSelectByCompleted(){
		//when
		List<Todo> selectedTodo = dao.selectByCompleted(1);
		
		//then
		assertThat(selectedTodo.size(), is(1));
	}
}
