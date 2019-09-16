package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.test.RootTestConfig;
import kr.or.ddit.prod.model.Prod;

public class ProdServiceTest extends RootTestConfig{

	@Resource(name = "prodService")
	private IProdService prodService;
	
	@Test
	public void getProdTest() {
		/***Given***/

		/***When***/
		List<Prod> list = prodService.getProd("P101");
		
		/***Then***/
		assertEquals(6, list.size());
	}
	
}
