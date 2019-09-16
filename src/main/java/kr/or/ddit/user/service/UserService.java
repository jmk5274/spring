package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.User;

@Service
public class UserService implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	//class 명에서 맨 첫글자를 소문자로 변경한 문자열이 스프링 빈 이름으로 기본 설정
	//다른이름의 스프링 빈이름으로 등록을 하려면 속성을 설정
	@Resource(name = "userDao")
	private IUserDao userDao;
	
	public UserService() { }
	
	public UserService(IUserDao userDao) {
		this.userDao = userDao;
	}

	/**
	* Method : getUserList
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<User> getUserList() {
		logger.debug("getUserList()");
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return userDao.getUserList();
	}

	/**
	 * 
	* Method : getUser
	* 작성자 : PC-13
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 상세 조회
	 */
	@Override
	public User getUser(String userId) {
		return userDao.getUser(userId);
	}

	/**
	 * 
	* Method : getUserListOnlyHalf
	* 작성자 : PC-13
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 리스트중 50명 임의 조회
	 */
	@Override
	public List<User> getUserListOnlyHalf() {
		return userDao.getUserListOnlyHalf();
	}

	/**
	* Method : getUserPagingList
	* 작성자 : PC-13
	* 변경이력 :
	* @param sqlSession
	* @param page
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Override
	public Map<String, Object> getUserPagingList(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<User> userList = userDao.getUserPagingList(page);
		int totalCnt = userDao.getUserTotalCnt();
		
		map.put("userList", userList);
		map.put("totalCnt", totalCnt);
		map.put("paginationSize", (int)Math.ceil((double)totalCnt / page.getPagesize()));
		
		return map;
	}

	@Override
	public int insertUser(User user) {
		//선언적트랜잭션
		// . 예외가 발생 했을 때 정상적으로 rollback이 되는지
		// . 예외가 발생하지 않고 정상적으로 코드가 실행되었을때 명시적인 commit없는데 commit이 정상적으로 되는지
		
		return userDao.insertUser(user);
	}

	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}

	@Override
	public int modifyUser(User user) {
		return userDao.modifyUser(user);
	}
}
