package kr.co.torpedo.uims.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	@RequestMapping("/")
	public String HelloSpring() {
		return "Hello Spring";
	}
}
