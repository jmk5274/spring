package kr.or.ddit.prod.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import kr.or.ddit.prod.service.IProdService;

@Controller
public class ProdController {

	@Resource(name = "prodService")
	private IProdService prodService;
	
}
