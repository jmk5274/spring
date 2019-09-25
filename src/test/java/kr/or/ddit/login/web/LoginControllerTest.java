package kr.or.ddit.login.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.config.spring.WebTestConfig;
import kr.or.ddit.user.model.User;

public class LoginControllerTest extends WebTestConfig{

	private static final Logger logger = LoggerFactory.getLogger(LoginControllerTest.class);
	
	/**
	* Method : LoginViewTest
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* Method 설명 : 로그인 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void LoginViewTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("login/login", mav.getViewName());
	}

	@Test
	public void LoginProcess() throws Exception {
		MockHttpSession session = new MockHttpSession();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("userId", "brown");
		params.add("pass", "brown1234");
		
//		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId", "brown").param("pass", "brown1234")).andReturn();
		mockMvc.perform(post("/login").params(params).session(session)).andExpect(status().isOk()).andExpect(view().name("main"));
		
		User user = (User) session.getAttribute("S_USERVO");
		logger.debug("user : {}", user);
		
		assertNotNull(user);
	}
	
}
