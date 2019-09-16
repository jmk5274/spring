package kr.or.ddit.prod.repository;

import java.util.List;

import kr.or.ddit.prod.model.Prod;

public interface IProdDao {
	public List<Prod> getProd(String lprod_gu);
}
