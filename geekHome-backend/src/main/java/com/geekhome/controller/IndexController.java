package com.geekhome.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geekhome.entity.Admin;
import com.geekhome.shiro.config.AdminShiroUtil;

/**
 * @Description: 后台首页
 * @author handx  
 * @date 2017年9月13日 下午11:11:48 
 * @version V1.0
 */

@Controller
@RequestMapping("index")
public class IndexController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("indexPage")
	public String indexPage() {
		Admin admin = AdminShiroUtil.getUserInfo();
		
		System.out.println("==================");
		return "index";
	}
	
	@RequestMapping("welcomePage")
	public String welcomePage() {
		return "welcome";
	}

}
