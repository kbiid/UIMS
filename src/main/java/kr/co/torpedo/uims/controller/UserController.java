package kr.co.torpedo.uims.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.torpedo.uims.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(HttpServletRequest httpServletRequest) {
		logger.info("register");
		if (!checkSession(httpServletRequest)) {
			logger.info("Don't have session");
			return "redirect:/";
		}
		return "register";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam("id") int id, Model model, HttpServletRequest httpServletRequest) {
		logger.info("detail");
		if (!checkSession(httpServletRequest)) {
			logger.info("Don't have session");
			return "redirect:/";
		}
		model.addAttribute("User", userService.selectUser(id));
		return "detail";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam("id") int id, Model model, HttpServletRequest httpServletRequest) {
		logger.info("update");
		if (!checkSession(httpServletRequest)) {
			logger.info("Don't have session");
			return "redirect:/";
		}
		model.addAttribute("User", userService.selectUser(id));
		return "update";
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestBody Map<String, Object> params, HttpServletRequest httpServletRequest) {
		logger.info("delete");
		if (!checkSession(httpServletRequest)) {
			logger.info("Don't have session");
			return "redirect:/";
		}
		userService.delete(Integer.parseInt(params.get("id").toString()));
		logger.info("delete success");
		return "redirect:/list";
	}

	private boolean checkSession(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		if (session.getAttribute("Admin") == null) {
			return false;
		}
		return true;
	}
}
