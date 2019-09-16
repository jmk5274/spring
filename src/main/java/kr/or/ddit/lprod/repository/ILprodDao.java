package kr.or.ddit.lprod.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.Lprod;

public interface ILprodDao {
	
	public List<Lprod> selectLprodList();
	
	public List<Lprod> pagingLprodList(Page page);
	
	public int getLprodTotalCnt();
}
