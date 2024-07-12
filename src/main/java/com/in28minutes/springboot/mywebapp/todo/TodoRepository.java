package com.in28minutes.springboot.mywebapp.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	public List<Todo> findAllByUsername(String username);
	public Todo findByUsername(String username);
	
}
