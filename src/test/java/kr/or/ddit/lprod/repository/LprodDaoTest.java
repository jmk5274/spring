package kr.or.ddit.lprod.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.lprod.model.Lprod;
import kr.or.ddit.lprod.repository.ILprodDao;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodService;


public class LprodDaoTest extends RootTestConfig {

	@Resource(name="lprodDao")
	private ILprodDao dao;
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void selectLprodListTest() {
		/*** Given ***/

		/*** When ***/
		List<Lprod> list = dao.selectLprodList();

		/*** Then ***/
		assertEquals(10, list.size());
	}

	@Test
	public void pagingLprodListTest() {
		/***Given***/
		Page page = new Page();
		page.setPage(1);
		page.setPagesize(5);

		/***When***/
		List<Lprod> list = dao.pagingLprodList(page);
		
		/***Then***/
		assertEquals(5, list.size());
	}
	
	@Test
	public void getLprodTotalCntTest() {
		/***Given***/
		

		/***When***/
		int totalCnt = dao.getLprodTotalCnt();
		
		/***Then***/
		assertEquals(10, totalCnt);
	}
}
