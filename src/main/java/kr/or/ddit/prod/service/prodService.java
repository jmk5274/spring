package kr.or.ddit.prod.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.prod.model.Prod;
import kr.or.ddit.prod.repository.IProdDao;

@Service
public class prodService implements IProdService{

	@Resource(name = "prodDao")
	private IProdDao prodDao;
	
	@Override
	public List<Prod> getProd(String lprod_gu) {
		return prodDao.getProd(lprod_gu);
	}

}
