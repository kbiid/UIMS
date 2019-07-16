package kr.co.torpedo.uims.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.torpedo.uims.paging.Criteria;
import kr.co.torpedo.uims.paging.PageMaker;
import kr.co.torpedo.uims.service.UserService;

@Controller
public class PageController {
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listPage(@ModelAttribute("cri") Criteria cri, Model model, HttpServletRequest httpServletRequest) {
		logger.info("listPage");
		model.addAttribute("list", userService.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(userService.countUser());
		model.addAttribute("pageMaker", pageMaker);
		return "viewUserList";
	}

	@RequestMapping(value = "/typography")
	public String board(HttpServletRequest httpServletRequest) {
		logger.info("board");
		return "typography";
	}

	@RequestMapping(value = "/widgets")
	public String info(HttpServletRequest httpServletRequest) {
		logger.info("widgets");
		return "widgets";
	}
}
