package kr.or.ddit.login.web;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.user.model.User;
import kr.or.ddit.user.service.IUserService;

@Controller
public class LoginController {

	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : view
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @return
	* Method 설명 : 로그인 화면 요청 처리
	*/
	@RequestMapping(path = "login", method = RequestMethod.GET)
	public String view() {
		return "login/login";
	}
	
	//로그인 요청 처리
	/**
	* Method : loginProcess
	* 작성자 : JEON MIN GYU
	* 변경이력 : 
	* @param userId
	* @param pass
	* @param rememberMe
	* @param response
	* @param session
	* @return
	* Method 설명 : 로그인 요청 처리
	*/
	@RequestMapping(path = "login", method = RequestMethod.POST)
	public String loginProcess(String userId, String pass, String rememberMe, HttpServletResponse response, HttpSession session) {
		
		manageUserIdCookie(response, userId, rememberMe);
		
		User user = userService.getUser(userId);
		
		if(user==null) {
			return "login/login";
		}else if(user.checkLoginValidate(userId, pass)) {
			session.setAttribute("S_USERVO", user);
			return "main";
		}else {
			return "login/login";
		}
		
	}
	
	private void manageUserIdCookie(HttpServletResponse response, String userId, String rememberMe) {
	     //rememberMe 파라미터가 존재할 경우 userId를 cookie로 생성
	     Cookie cookie = new Cookie("userId", userId);
	     if(rememberMe != null) {
	        cookie.setMaxAge(60*60*24*30); //30일
	     }else {
	          cookie.setMaxAge(0); //삭제
	     }
	        
	     response.addCookie(cookie);
	}
	
}
