package com.yao.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class OurDemoInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = request.getRequestURI();
		Map<String, String[]>  map=request.getParameterMap();
	//	Set<String> strKey = map.keySet();
		/*for (Entry<String, String[]> entry : map.entrySet()) {
			String[] strArr = entry.getValue();
			String para = "";
			for (int i = 0; i < strArr.length; i++) {
				para += strArr[i];
				System.out.println(strArr[i].length());
			}
			System.out.println("key= " + entry.getKey() + " and value= " + RSAUtils.decryptPrivate(para));
		}*/

		/*for (int i=0;i<map.keySet().size();i++) {
			RSAUtils.decryptPrivate(strKey.);
		}*/
		String[] uri = requestUrl.split("/");
		System.out.println("拦截器设置打印");
		return super.preHandle(request, response, handler);
	}
}
