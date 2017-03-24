package com.yao.admin.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.yao.model.UserModel;
import com.yao.model.UserModelExample;
import com.yao.service.UserService;
import com.yao.vo.EasyUIParamVO;
import com.yao.vo.PageVO;

@Controller
@RequestMapping(value = "/admin")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService service;

	/**
	 *  获取用户列表
	 * @param user
	 * @param page
	 * @param easyUIParam
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
	public JSONObject getAllUser(UserModel user, PageVO page,
			EasyUIParamVO easyUIParam) {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			UserModelExample example = new UserModelExample();
			int totalCount = (int) service.countByExample(example);
			page.setTotalRow(totalCount);
			example.setPage(page);
			example.setEasyUIParam(easyUIParam);
			List<UserModel> users = service.selectByExample(example);
			result.put("users", users);
			result.put("totalCount", totalCount);
			json.put("result", result);
			json.put("message", "获取用户信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("errorID", 1);
			json.put("message", "获取用户信息失败，请重试！");
		}
		return json;
	}
	
	/**
	 * 更改用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public JSONObject updateUserInfo(HttpServletRequest request,UserModel user) {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 判断 request 是否有文件上传,即多部分请求
			if (multipartResolver.isMultipart(request)) {
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null) {
						// 取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (myFileName.trim() != "") {
							// 获取文件后缀名
							String houzhuiming = myFileName.substring(
									myFileName.lastIndexOf(".")).toLowerCase();
							// 定义上传路径
							String path = request.getSession().getServletContext()
									.getRealPath(File.separator);
							File webrootPath = new File(path + "/" + user.getId()
									+ "/");
							if (!webrootPath.isDirectory()) {
								webrootPath.mkdirs();
							}
							String realpath=new Date().getTime()+ (int) (Math.random() * 100)+ houzhuiming;
							// 重命名上传后的文件名
							path = webrootPath + "/" +realpath;
							File localFile = new File(path);
							file.transferTo(localFile);
							
							user.setUserphoto("/" + user.getId() + "/"+ realpath);
						}
					}
				}

			}
			int totalCount=service.updateByPrimaryKeySelective(user);
			json.put("result", totalCount);
			json.put("message", "获取用户信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("errorID", 1);
			json.put("message", "获取用户信息失败，请重试！");
		}
		return json;
	}
	
}
