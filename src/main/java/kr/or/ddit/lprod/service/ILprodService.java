package kr.or.ddit.lprod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.Lprod;

public interface ILprodService {

	public List<Lprod> selectLprodList();
	
	public Map<String, Object> pagingLprodList(Page page);
	
}
