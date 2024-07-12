package com.in28minutes.springboot.mywebapp.todo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	@Autowired
	private TodoRepository todoRepository;

	@RequestMapping("/list-todos")
	public String listAllTodos(Model model) {
		model.addAttribute("todos", todoRepository.findAllByUsername((String)model.getAttribute("name")));
		return "listTodos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showNewTodoPage() {
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addNewTodo(@Valid Todo todo, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = new LinkedList<>();
			for (ObjectError error : bindingResult.getAllErrors())
				errorMessages.add(error.getDefaultMessage());
			model.addAttribute("error", errorMessages);
		} else {
			todoRepository.save(todo);
			model.addAttribute("success", "Todo was added successfully! Add another?");
			model.addAttribute("todo", null);
		}
		return "todo";
	}

	@RequestMapping(value = "/edit-todo", method = RequestMethod.GET)
	public String showEditTodoPage(int id, Model model) {
		Todo todo = todoRepository.findById(id).get();
		if(todo==null) {
			model.addAttribute("error", "* Todo wasn't found. Are you playing with the id?");
			return "listTodos";
		}
		else
			model.addAttribute("todo",todo);
		return "todo";
	}

	@RequestMapping(value = "/edit-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			List<String> errorMessages = new LinkedList<>();
			for (ObjectError error : bindingResult.getAllErrors())
				errorMessages.add(error.getDefaultMessage());
			model.addAttribute("error", errorMessages);
		} else {
			todoRepository.save(todo);
			model.addAttribute("success", "Todo was updated!");
		}
		return "todo";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.POST)
	public String deleteTodo(int id, RedirectAttributes redirectAttributes) {
		Todo todo = todoRepository.findById(id).get();
		if (todo != null) {
			todoRepository.deleteAllById(Arrays.asList(id));
			redirectAttributes.addFlashAttribute("success", "Todo(s) deleted successfully");
		}
		else 
			redirectAttributes.addFlashAttribute("error", "* Todo wasn't found. Are you playing with the id?");
		return "redirect:list-todos";
	}

	@ModelAttribute("todo")
	public Todo initializeTodo(HttpSession session) {
		return new Todo(0, (String)session.getAttribute("name"), "", null, false);
	}
}
