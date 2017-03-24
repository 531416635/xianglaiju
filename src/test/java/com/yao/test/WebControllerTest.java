package com.yao.test;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value = "/web")
public class WebControllerTest {
	/**
	 * 
	 * PC页面拦截
	 * 
	 * @param request
	 * @param pagename
	 * @return
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String pagename(HttpServletRequest request) {
		return "test";
	}

	/**
	 * ajax请求获取string数据测测试
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tojsontest", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object getJson() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", "33");
		System.out.println(JSON.toJSONString(map));
		return JSON.toJSONString(map);
	}

	/**
	 * ajax请求获取json数据的测试
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tojsontest1", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getJson1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", "33");
		System.out.println(JSON.toJSONString(map));
		return map;
	}
}
