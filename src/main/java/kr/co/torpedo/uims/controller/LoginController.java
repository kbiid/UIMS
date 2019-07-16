package kr.co.torpedo.uims.controller;

import java.security.NoSuchAlgorithmException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.torpedo.uims.domain.Admin;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private Admin admin;

	// 초기화를 위한 작업
	@PostConstruct
	public void init() {
		logger.info("controller init");
		admin = new Admin();
	}

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

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String checkInput(HttpServletRequest httpServletRequest, Model model) throws NoSuchAlgorithmException {
		logger.info("checkInput");
		checkSession(httpServletRequest);
		String id = httpServletRequest.getParameter("inputId");
		String passwd = httpServletRequest.getParameter("passwd");
		if (this.admin.checkAdminInfo(id, passwd)) {
			logger.info("login success");
			logger.info("make session");
			HttpSession session = httpServletRequest.getSession(true);
			session.setAttribute("Admin", admin);
			return "redirect:/list";
		}
		logger.info("login fail");
		return "loginFail";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest httpServletRequest, Model model) throws NoSuchAlgorithmException {
		logger.info("logout");
		checkSession(httpServletRequest);
		return "redirect:/";
	}
}
