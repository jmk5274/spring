package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.Lprod;
import kr.or.ddit.lprod.repository.ILprodDao;

@Service
public class LprodService implements ILprodService{

	@Resource(name = "lprodDao")
	private ILprodDao dao;
	
	public LprodService() {
	}
	
	@Override
	public List<Lprod> selectLprodList() {
		List<Lprod> list = dao.selectLprodList();
		return list;
	}

	@Override
	public Map<String, Object> pagingLprodList(Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Lprod> list = dao.pagingLprodList(page);
		int totalCnt = dao.getLprodTotalCnt();
		int paginationSize = (int) Math.ceil((double)totalCnt/page.getPagesize());
		
		map.put("lprodList", list);
		map.put("paginationSize", paginationSize);
		
		return map;
	}
	
}
