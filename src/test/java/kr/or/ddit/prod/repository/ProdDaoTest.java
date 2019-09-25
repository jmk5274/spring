package kr.or.ddit.prod.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.spring.RootTestConfig;
import kr.or.ddit.prod.model.Prod;

public class ProdDaoTest extends RootTestConfig{

	@Resource(name = "prodDao")
	private IProdDao prodDao;
	
	@Test
	public void getProdTest() {
		/***Given***/

		/***When***/
		List<Prod> list = prodDao.getProd("P101");
		
		/***Then***/
		assertEquals(6, list.size());
	}

}
