package kr.or.ddit.lprod.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.common.model.Page;
import kr.or.ddit.lprod.model.Lprod;

@Repository
public class LprodDao implements ILprodDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Lprod> selectLprodList() {
		return sqlSession.selectList("lprod.selectLprodList");
	}

	@Override
	public List<Lprod> pagingLprodList(Page page) {
		return sqlSession.selectList("lprod.pagingLprodList", page);
	}

	@Override
	public int getLprodTotalCnt() {
		return sqlSession.selectOne("lprod.getLprodTotalCnt");
	}

	
}
