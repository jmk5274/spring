package kr.or.ddit.user.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.config.spring.WebTestConfig;

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
	
	/**
	* Method : user
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 조회 테스트
	*/
	@Test
	public void user() throws Exception {
		mockMvc.perform(get("/user/user").param("userId", "brown"))
				.andExpect(model().attributeExists("user"))
				.andExpect(view().name("user/user"));
	}
	
	/**
	* Method : userFormViewTest
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 등록 이동 테스트
	*/
	@Test
	public void userFormViewTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		assertEquals("user/userForm", mav.getViewName());
	}
	
	/**
	* Method : userFormTest
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* Method 설명 : 사용자 등록 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void userFormTest() throws Exception {
		
		File f = new File("src/test/resources/kr/or/ddit/test/brown.png");
		FileInputStream fis = new FileInputStream(f);
		
		MockMultipartFile file = new MockMultipartFile("picture", "brown.png", "", fis);
		
		mockMvc.perform(fileUpload("/user/userForm")
				 .file(file)
		         .param("userId", "brownTest")
		         .param("userNm", "브라운테스트")
		         .param("alias", "곰테스트")
		         .param("reg_dt", "2019-08-08")
		         .param("addr1", "대전광역시 중구 중앙로 76")
		         .param("addr2", "영민빌딩 2층 DDIT")
		         .param("zipcode", "34940")
		         .param("pass", "brownTest1234"))
			 .andExpect(status().is(302));
//			 .andExpect(view().name("redirect:user/user"));
	}
	
	/**
	* Method : modifyUserViewTest
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 수정 이동 테스트
	*/
	@Test
	public void modifyUserViewTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/modifyUser").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		
		/***Then***/
		assertEquals("user/modifyUser", mav.getViewName());
	}
	
	/**
	* Method : modifyUserTest
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 정보 수정 테스트
	*/
	@Test
	public void modifyUserTest() throws Exception {
		File f = new File("src/test/resources/kr/or/ddit/test/brown.png");
		FileInputStream fis = new FileInputStream(f);
		
		MockMultipartFile file = new MockMultipartFile("picture", "brown.png", "", fis);
		
		mockMvc.perform(fileUpload("/user/modifyUser")
				 .file(file)
		         .param("userId", "brown")
		         .param("userNm", "브라운테스트")
		         .param("alias", "곰테스트")
		         .param("reg_dt", "2019-08-08")
		         .param("addr1", "대전광역시 중구 중앙로 76")
		         .param("addr2", "영민빌딩 2층 DDIT")
		         .param("zipcode", "34940")
		         .param("pass", "brownTest1234"))
			 .andExpect(status().is(302));
	}
}
