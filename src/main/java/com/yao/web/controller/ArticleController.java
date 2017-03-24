package com.yao.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class ArticleController {

	@RequestMapping(value="/toArticle")
	public String toArticle(){
		
		return "website/article";
	}
}
