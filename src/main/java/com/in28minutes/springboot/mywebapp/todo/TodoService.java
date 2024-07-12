package com.in28minutes.springboot.mywebapp.todo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
//	private Logger logger = LoggerFactory.getLogger(getClass());
	private static List<Todo> todos;
	static {
		todos = new LinkedList<Todo>(Arrays.asList(
				new Todo(1, "sadek", "sleep", LocalDate.now(), false),
				new Todo(2, "sadek", "brush before sleep hehe", LocalDate.now(), false),
				new Todo(3, "mahdi", "finish my angular internship", LocalDate.now().minusMonths(2), true),
				new Todo(4, "sadek", "finish my web app freelance", LocalDate.now().plusDays(7), false),
				new Todo(5, "ali", "finish Ranga course", LocalDate.now().plusDays(10), false)));
	}

	public List<Todo> findByUserName(String username) {
		Predicate<? super Todo> predicate = todo->todo.getUsername().equals(username);
		return todos.stream().filter(predicate).toList();
	}

	public Todo findById(int id) {
		for (int i = 0; i < todos.size(); i++)
			if (id == todos.get(i).getId())
				return todos.get(i);
		return null;
	}

	public boolean addNewTodo(Todo todo) {
		if (todos.size() > 0)
			todo.setId(todos.get(todos.size() - 1).getId() + 1);
		todos.add(todo);
		System.out.println(todo);
		return true;
	}

	public boolean updateTodo(Todo todo) {
		for (int i = 0; i < todos.size(); i++)
			if (todo.getId() == todos.get(i).getId()) {
				todos.remove(i);
				todos.add(i, todo);
				return true;
			}
		return false;
	}

	public boolean deleteTodo(int id) {
		for (Todo todo : todos)
			if (todo.getId() == id) {
				todos.remove(todo);
				return true;
			}
		return false;
	}

	public int deleteById(int id) {
		int count;
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		count = (int) todos.stream().filter(predicate).count();
		todos.removeIf(predicate);
		return count;
	}
}
