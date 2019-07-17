package kr.co.torpedo.uims.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String home(HttpServletRequest httpServletRequest) {
		logger.info("home method");
		checkSession(httpServletRequest);
		return "login";
	}

	private void checkSession(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		if (session.getAttribute("Admin") != null) {
			logger.info("remove session");
			session.invalidate();
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest httpServletRequest, Model model){
		logger.info("logout");
		checkSession(httpServletRequest);
		return "redirect:/";
	}
}
