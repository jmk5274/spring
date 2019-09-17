package kr.or.ddit.lprod.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.Lprod;
import kr.or.ddit.lprod.service.ILprodService;

@Controller
@RequestMapping("lprod")
public class LprodController {

	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
	private static final Logger logger = LoggerFactory.getLogger(LprodController.class);
	/**
	* Method : lprodList
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 제품 전체 리스트 조회
	*/
	@RequestMapping(path = "lprodList", method = RequestMethod.GET)
	public String lprodList(Model model) {
		
		List<Lprod> lprodList = lprodService.selectLprodList();
		model.addAttribute("lprodList",lprodList);
		
		return "lprod/lprodList";
	}
	
	/**
	* Method : lprodPagingList
	* 작성자 : JEON MIN GYU
	* 변경이력 :
	* @param page
	* @param model
	* @return
	* Method 설명 : 제품 페이징 리스트 조회
	*/
	@RequestMapping(path = "lprodPagingList", method = RequestMethod.GET)
	public String lprodPagingList(Page page, Model model) {
		
		page.setPagesize(5);
		Map<String, Object> map = lprodService.pagingLprodList(page);
		
		model.addAttribute("pageVo", page);
		model.addAllAttributes(map);
//		model.addAttribute("lprodList",lprodList);
		
		return "lprod/lprodList";
	}
}
