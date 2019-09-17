package kr.or.ddit.user.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;

public class UserControllerTest extends WebTestConfig{
	
	/**
	* Method : userListTest
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* Method 설명 : 사용자 전체 리스트 조회 테스트
	 * @throws Exception 
	*/
	@Test
	public void userListTest() throws Exception {

		mockMvc.perform(get("/user/userList"))
		.andExpect(model().attributeExists("userList"))
		.andExpect(view().name("user/userList"));

	}
	
	/**
	* Method : userListOnlyHalfTest
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 유저리스트 Half 조회
	*/
	@Test
	public void userListOnlyHalfTest() throws Exception {
		
		mockMvc.perform(get("/user/userListOnlyHalf"))
				.andExpect(model().attributeExists("userList"))
				.andExpect(view().name("user/userListOnlyHalf"));
		
	}
	
	/**
	* Method : userPagingListTest
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 유저 페이징 리스트 조회
	*/
	@Test
	public void userPagingListTest() throws Exception {
		
		mockMvc.perform(get("/user/userPagingList"))
				.andExpect(model().attributeExists("userList"))
				.andExpect(model().attributeExists("paginationSize"))
				.andExpect(view().name("user/userPagingList"));
		
	}
	
}
