package kr.or.ddit.user.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.user.model.User;
import kr.or.ddit.user.model.UserValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.FileUtil;
import kr.or.ddit.util.model.FileInfo;

@RequestMapping("user")
@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : userView
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 상세화면 요청
	*/
	//사용자가 localhost:8081/spring/user/view
	@RequestMapping("view.do")
	public String userView() {
		logger.debug("userController.userView()");
		return "user/view";
		
		//prefix + viewName + suffix
		//WEB-INF/views/user/view.jsp
	}
	
	/**
	* Method : userList
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@RequestMapping(path = "userList", method = RequestMethod.GET)
	public String userList(Model model) {
		//사용자 정보 전체 조회
		List<User> userList = userService.getUserList();
		model.addAttribute("userList", userList);
		
		return "user/userList";
	}
	
	/**
	* Method : userListOnlyHalf
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 half 리스트 조회
	*/
	@RequestMapping(path = "userListOnlyHalf", method = RequestMethod.GET)
	public String userListOnlyHalf(Model model) {

		List<User> userList = userService.getUserListOnlyHalf();
		model.addAttribute("userList", userList);
				
		return "user/userListOnlyHalf";
	}
	
	/**
	* Method : userPagingList
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param page
	* @param model
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	@RequestMapping(path = "userPagingList", method = RequestMethod.GET)
	public String userPagingList(Page page, Model model) {
		
		Map<String, Object> map = userService.getUserPagingList(page);
		
		model.addAttribute("pageVo", page);
		model.addAllAttributes(map);
		
		return "user/userPagingList";
	}
	
	/**
	* Method : user
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param model
	* @param userId
	* @return
	* Method 설명 : 사용자 조회 화면 요청
	*/
	@GetMapping("user")
	public String user(Model model, String userId) {
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user", user);
		
		
		
		return "user/user";
	}
	
	/**
	* Method : userPicture
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param userId
	* @param response
	* @throws IOException
	* Method 설명 : 사용자 사진 출력
	*/
	@GetMapping("userPicture")
	public void userPicture(String userId, HttpServletResponse response) throws IOException {
		User user = userService.getUser(userId);
		
		ServletOutputStream sos = response.getOutputStream();
		
		File picture = new File(user.getRealfilename());
		FileInputStream fis = new FileInputStream(picture);
		
		byte[] buff = new byte[512];
		int len = 0;
		
		while((len = fis.read(buff, 0, 512)) != -1) {
			sos.write(buff,0,len);
		}
		fis.close();
	}
	
	/**
	* Method : userFormView
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param model
	* @param user
	* @return
	* Method 설명 : 사용자 등록화면 요청 테스트
	*/
	@GetMapping("userForm")
	public String userFormView() {
		return "user/userForm";
	}
	
	/**
	* Method : userForm
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param user
	* @param result
	* @param picture
	* @return
	* Method 설명 : 사용자 등록
	*/
	@PostMapping("userForm")
	public String userForm(User user, BindingResult result, @RequestPart("picture") MultipartFile picture) {
		
		new UserValidator().validate(user, result);
		
		if(result.hasErrors()) {
			return "user/userForm";
		}else {
			FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());
		
			//첨부된 파일이 있을 경우만 업로드 처리
			if(picture.getSize() > 0) {
				try {
					picture.transferTo(fileInfo.getFile());
					user.setFilename(fileInfo.getOriginalFileName());		//originalFileName
					user.setRealfilename(fileInfo.getFile().getPath());
					
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			int insertCnt = userService.insertUser(user);
			
			if(insertCnt == 1) {
				//redirect
				return "redirect:/user/user?userId=" + user.getUserId();
			}else {
				//forward
				return "user/userForm";
			}
		}
	}
	
	/**
	* Method : modifyUserView
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param model
	* @param userId
	* @return
	* Method 설명 : 사용자 수정 화면 요청
	*/
	@GetMapping("modifyUser")
	public String modifyUserView(Model model, String userId) {
		
		User user = userService.getUser(userId);
		
		model.addAttribute("user", user);
		
		return("user/modifyUser");
	}
	
	/**
	* Method : modifyUser
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param user
	* @param result
	* @param picture
	* @return
	* Method 설명 : 사용자 수정
	*/
	@PostMapping("modifyUser")
	public String modifyUser(User user, BindingResult result, @RequestPart("picture") MultipartFile picture) {
		
		User userVo = userService.getUser(user.getUserId());
		
		user.setFilename(userVo.getFilename());
		user.setRealfilename(userVo.getRealfilename());
		
		FileInfo fileInfo = FileUtil.getFileInfo(picture.getOriginalFilename());
		
		//첨부된 파일이 있을 경우만 업로드 처리
		if(picture.getSize() > 0) {
			try {
				picture.transferTo(fileInfo.getFile());
				user.setFilename(fileInfo.getOriginalFileName());		//originalFileName
				user.setRealfilename(fileInfo.getFile().getPath());
				
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int updateCnt = userService.modifyUser(user);
		
		if(updateCnt == 1) {
			//redirect
			return "redirect:/user/user?userId=" + user.getUserId();
		}else {
			//forward
			return "user/modifyUser?user="+user;
		}
	}
	
	
	
}
