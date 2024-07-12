package com.in28minutes.springboot.mywebapp.login;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
//	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = {"/","/welcome","/home"}, method = RequestMethod.GET)
	public String goTowelcomePage(Model model) {
		return "welcome";
	}
	
//	private String getLoggedInUsername() {
//		return SecurityContextHolder.getContext().getAuthentication().getName();
//	}
}
