package kr.or.ddit.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.User;

@Service
public class UserService implements IUserService {

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
		
		return userDao.getUserList();
	}

}
