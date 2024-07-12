package com.in28minutes.springboot.mywebapp.todo;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {
	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_seq")
	 @SequenceGenerator(name = "custom_seq", sequenceName = "custom_sequence", allocationSize = 1)
	private int  id;
	@Size(min=1, message = "Username can't be empty")
	private String username;
	@Size(min=10, message = "Description can't be less than 10 characters")
	private String description;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate targetDate;
	private boolean done;
	
	public Todo () {
		
	}
	public Todo(Integer id, String username, String description, LocalDate targetDate, Boolean done) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", done=" + done + "]";
	}
}