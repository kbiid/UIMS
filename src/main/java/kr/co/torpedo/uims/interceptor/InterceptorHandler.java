package kr.co.torpedo.uims.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
@Qualifier("interceptorHandler")
public class InterceptorHandler extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(InterceptorHandler.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("check session");
		HttpSession session = request.getSession();
		if (session.getAttribute("Admin") == null) {
			response.sendRedirect("/");
			logger.info("session not exist");
			return false;
		}
		logger.info("session exist");
		return true;
	}
}
