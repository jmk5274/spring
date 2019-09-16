package kr.or.ddit.lprod.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.lprod.model.Lprod;

public class LprodServiceTest extends RootTestConfig{

	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
	/**
	 * Method : test
	 * 작성자 : PC-13
	 * 변경이력 :
	 * Method 설명 : getUserList 테스트
	 */
	@Test
	public void pagingLprodListTest() {
		/***Given***/
		Page page = new Page();
		page.setPage(1);
		page.setPagesize(5);
		
		/***When***/
		Map<String, Object> map = lprodService.pagingLprodList(page);
		List<Lprod> lprodList = (List<Lprod>) map.get("lprodList");
		int totalCnt = (Integer) map.get("paginationSize");
		
		/***Then***/
		assertEquals(5, lprodList.size());
		assertEquals(2, totalCnt);
	}
	
}
