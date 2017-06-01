
	var filter_mode = 'All'; //리스트 필터 모드를 전역으로 선언 (All, Active, Completed)
	
	$(function(){
		//페이지 로드 시 리스트 갱신
		showList(filter_mode);
		
		
		//입력창 엔터 이벤트 처리
		$('.new-todo').keyup(function(e){
			if(e.keyCode == 13){
				insertTodo();
			}
		});
		
		/******Event delegation 방식**********/
		//체크 toggle버튼에 대한 클릭이벤트 핸들러 할당
		$('.todo-list').on('click','.toggle', function(e){
			var $li = $(e.target).parent().parent();
			var isCompleted = 0;	//0미완, 1완료
			
			if($li.attr("class") === "completed"){
				isCompleted = 0;
			}else{
				isCompleted = 1;
			}
			var id =  $li.attr("id").replace("list","");
			

			$.ajax({
				url:"api/todos/"+id,
				method:"PUT",
				data:JSON.stringify({"todo":$li.text(), "completed":isCompleted}),
				headers:{
					"Content-Type":"application/json"
				},
				success:function(data){
					showList(filter_mode);
				}
			});
		});//체크버튼
		
		//삭제버튼에 대한 클릭이벤트 핸들러 할당
		$('.todo-list').on('click', '.destroy',function(e){
			var $li = $(e.target).parent().parent();
			var id =  $li.attr("id").replace("list","");
			
			$.ajax({
				url:"api/todos/"+id,
				method:"DELETE",
				success:function(data){
					showList(filter_mode);
				}
			});
		});//삭제버튼
		
		
		/*********  Event Delegation  ***********/
		
		
		//필터 버튼 이벤트 처리
		$('#filterAll, #filterActive, #filterCompleted').click(function(e){
			e.preventDefault();//<a>태그의 기본 이벤트 기능 방지
			
			filter_mode = $(e.target).attr('id').replace('filter', '');
			var all = $('#filterAll');
			var active = $('#filterActive');
			var completed = $('#filterCompleted');
			
			if(filter_mode === 'All'){
				all.attr('class', 'selected');
				active.attr('class', '');
				completed.attr('class', '');
			}else if(filter_mode === 'Active'){
				all.attr('class', '');
				active.attr('class', 'selected');
				completed.attr('class', '');				
			}else if(filter_mode === 'Completed'){
				all.attr('class', '');
				active.attr('class', '');
				completed.attr('class', 'selected');
			}
			
			showList(filter_mode);
			
		});
		
		
	});//document.ready
	
	
	
	
	function insertTodo(){
		var content = $('.new-todo').val();
		if(content.trim() == ''){
			alert("내용을 입력하세요.");
		}else{
			//alert(content);
			$.ajax({
				url:"api/todos",
				method:"POST",
				data:JSON.stringify({"todo":content, "completed":0}),
				headers:{
					"Content-Type":"application/json"
				},
				success:function(data){
					//입력창 초기화
					$('.new-todo').val('');
					
					//리스트 갱신
					showList(filter_mode);
				}
			});//ajax
		}//else
	}//insertTodo()
	
	function showList(filter){
		var requestURL = "";
		if(filter === "Active"){
			requestURL = "/filter/0";
		}else if(filter === "Completed"){
			requestURL = "/filter/1";			
		}
		
		$.ajax({
			url:"api/todos"+requestURL,
			method:"GET",
			success:function(data){
				var html = "";
				var cnt = 0;
				for(var i=0; i<data.length; i++){
						html += '<li '+(data[i].completed == 0 ? "" : 'class="completed"')+' id="list'+data[i].id+'">';
						html += '<div class="view">';
						html += '<input class="toggle" type="checkbox" '+(data[i].completed == 0 ? "" : 'checked')+'>';
						html += '<label>'+data[i].todo+'</label>';
						html += '<button class="destroy"></button>';
						html += '</div>';
						html += '<input class="edit" value="Create a TodoMVC template">';
						html += '</li>';
					
						cnt++;
				}//for
				
				$(".todo-list").html(html);
				$(".todo-count strong").html(cnt);
				
			}
		});//ajax
	}//showList()
	

	
	function clearCompleted(){
		$.ajax({
			url:"api/todos/completed-item",
			method:"DELETE",
			success:function(data){
				showList(filter_mode);
			}
		});
	}
	
