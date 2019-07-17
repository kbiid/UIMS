package kr.co.torpedo.uims.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.torpedo.uims.domain.User;
import kr.co.torpedo.uims.service.UserService;

@Controller
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@Value("${admin.id}")
	private String id;
	@Value("${admin.passwd}")
	private String passwd;

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public String checkLogin(HttpServletRequest req, HttpServletResponse rep) throws NoSuchAlgorithmException {
		logger.info("checkLogin");
		String inputId = req.getParameter("id");
		String inputPasswd = req.getParameter("passwd");
		if (checkAdminInfo(inputId, inputPasswd)) {
			logger.info("success login");
			HttpSession session = req.getSession(true);
			session.setAttribute("Admin", id);
			return "success";
		} else {
			return "fail";
		}
	}

	private boolean checkAdminInfo(String id, String passwd) throws NoSuchAlgorithmException {
		if (!this.id.equals(id) || !BCrypt.checkpw(passwd, this.passwd)) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/selectAllUser", method = RequestMethod.POST)
	public void getListByAjax(HttpServletRequest req, HttpServletResponse rep) throws IOException {
		logger.info("getList");
		List<User> userList = userService.selectAll();
		Gson gson = new Gson();
		String result = gson.toJson(userList);

		logger.info("send List");
		rep.setCharacterEncoding("UTF-8");
		rep.setContentType("text/html; charset=UTF-8");
		rep.getWriter().write(result);
	}

	@ResponseBody
	@RequestMapping(value = "/addUser")
	public String addUser(@RequestBody Map<String, Object> params) {
		logger.info("addUser");
		User user = new User();
		user.setFirstName(params.get("firstName").toString());
		user.setLastName(params.get("lastName").toString());
		user.setEmail(params.get("email").toString());
		user.setGender(params.get("gender").toString());
		user.setIpAddress(params.get("ip").toString());
		user.setDate(getNowDate());
		userService.insert(user);
		logger.info("addUser success");
		return "viewUserList";
	}

	public java.sql.Date getNowDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(new java.util.Date());
		Date date = java.sql.Date.valueOf(str);
		return date;
	}

	@ResponseBody
	@RequestMapping(value = "/updateUser")
	public String updateUser(@RequestBody Map<String, Object> params) {
		logger.info("updateUser");
		User user = new User();
		user.setId(Integer.parseInt(params.get("id").toString()));
		user.setFirstName(params.get("firstName").toString());
		user.setLastName(params.get("lastName").toString());
		user.setEmail(params.get("email").toString());
		user.setGender(params.get("gender").toString());
		user.setIpAddress(params.get("ip").toString());
		user.setDate(java.sql.Date.valueOf(params.get("date").toString()));
		userService.update(user);
		logger.info("updateUser success");
		return "viewUserList";
	}
}
