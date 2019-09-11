package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:kr/or/ddit/config/spring/context-root.xml", 
		"classpath:kr/or/ddit/config/spring/context-datasource.xml",
		"classpath:kr/or/ddit/config/spring/context-transaction.xml"})
public class UserServiceTest {

	@Resource(name = "userService")
	private IUserService userService;
	private String userId = "brownTest";
	
	@Before
	public void setup() {
		userService.deleteUser(userId);
	}
	
	//테스트에 공통적으로 사용한 자원을 해제
	@After
	public void tearDown() {
	}
	
	@Test
	public void getUserListTest() {
		/***Given***/

		/***When***/
		List<User> userList = userService.getUserList();
		
		/***Then***/
		assertTrue(userList.size()>=105);
	}

	/**
	* Method : getUserTest
	* 작성자 : PC-13
	* 변경이력 :
	* Method 설명 : 사용자 정보 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";

		/***When***/
		User userVo = userService.getUser(userId);
		
		/***Then***/
		assertEquals("브라운", userVo.getUserNm());
		assertEquals("c6347b73d5b1f7c77f8be828ee3e871c819578f23779c7d5e082ae2b36a44", userVo.getPass());
	}
	
	@Test
	public void getUserListOnlyHalfTest() {
		/***Given***/
		
		/***When***/
		List<User> list = userService.getUserListOnlyHalf();
		
		/***Then***/
		assertEquals(50, list.size());
	}
	
	@Test
	public void getUserPagingListTest() {
		/***Given***/
		Page page = new Page();
		page.setPage(3);
		page.setPagesize(10);

		/***When***/
		Map<String, Object> map = userService.getUserPagingList(page);
		List<User> userList = (List<User>) map.get("userList");
		int paginationSize = (Integer)map.get("paginationSize");
		
		/***Then***/
		assertEquals(10, userList.size());
		assertEquals("xuserid22", userList.get(0).getUserId());
		assertEquals(11, paginationSize);
	}
	
	@Test
	public void insertUserTest() throws ParseException {
		/***Given***/
		User user = new User();
		//'brownTest','브라운테스트','brownTest1234','2019-08-08','곰테스트','대전광역시 중구 중앙로 76','영민빌딩 2층 DDIT','34940'
				
		user.setUserId(userId);
		user.setUserNm("브라운테스트");
		user.setPass("brownTest1234");
		user.setReg_dt(new SimpleDateFormat("yyyy-mm-dd").parse("2019-08-08"));
		user.setAlias("곰테스트");
		user.setAddr1("대전광역시 중구 중앙로 76");
		user.setAddr2("영민빌딩 2층 DDIT");
		user.setZipcode("34940");
		user.setFilename("");
		user.setRealfilename("saldkfjasdlkf");
		user.setRealfilename2("");
		
		/***When***/
		int insertCnt = userService.insertUser(user);
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void modifyUserTest() {
		/***Given***/
		User user = new User();
		
		user.setUserId(userId);
		user.setUserNm("가나다");
		user.setAlias("별명");
		try {
			user.setReg_dt(new SimpleDateFormat("yyyy-mm-dd").parse("2019-08-08"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setAddr1("주소1");
		user.setAddr2("주소2");
		user.setPass("1231564");
		user.setZipcode("45454");
		user.setFilename("테스트");
		user.setRealfilename("테스트");
		user.setRealfilename2("테스트");

		/***When***/
		userService.insertUser(user);
		
		int cnt = userService.modifyUser(user);
		
		/***Then***/
		assertEquals(1, cnt);
	}
	
	
}
