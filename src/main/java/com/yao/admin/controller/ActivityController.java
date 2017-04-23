package com.yao.admin.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yao.model.ActivityModel;
import com.yao.model.PictureModel;
import com.yao.service.ActivityService;
import com.yao.utils.Base64;
import com.yao.utils.ImageUtil;
import com.yao.utils.SysConfig;
import com.yao.vo.PageVO;

@Controller
@RequestMapping(value = "/admin/activity")
public class ActivityController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ActivityService service;

	/**
	 * iframe跳转到活动管理页面
	 * @param response
	 * @return
	 */
	@RequestMapping("/activityManager.html")
	public String initMenu(HttpServletResponse response) {
		logger.debug("进入菜单管理页面");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		return "adminsite/activityManager";
	}

	/**
	 * 获取活动列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getActivityList", method = RequestMethod.POST)
	public JSONObject getMenuList(PageVO pageVO ) {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			int totalRow = service.selectCount(map);
			pageVO.setTotalRow(totalRow);
			map.put("pageVO", pageVO);
			List<ActivityModel> list = service.selectList(map);
			json.put("errorID", 1);
			json.put("result", list);
			json.put("msg", "查询成功");
		} catch (Exception e) {
			json.put("result", "");
			json.put("msg", "查询失败");
		}
		logger.debug(json.toJSONString());
		return json;
	}
	
	/**
	 * 新增
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveActivity", method = RequestMethod.POST)
	public JSONObject saveMenu(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		try {
			ActivityModel activityModel = new ActivityModel();
			activityModel.setCreatetime(new Date());
			
			/*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
				    .getAuthentication()
				    .getPrincipal();
			*/
			//定义上传路径---图片统一保存在  项目目录下面
			String relPath ="";
			relPath = request.getSession().getServletContext().getRealPath("")+SysConfig.picturePath;
			File webrootPath = new File(relPath);
			if (!webrootPath.isDirectory()) {
				webrootPath.mkdirs();
			}
			String activityContent=request.getParameter("activityContent");
			List<PictureModel> pictureList=new ArrayList<PictureModel>();//用于保存生成图片的路径
			PictureModel pictureModel = new PictureModel();
			pictureModel.setPictureType("activity");
			//pictureModel.setUserId(userDetails.getUsername());
			Pattern pattern = Pattern.compile("src=\"(.+?)\"");
			Matcher matcher = pattern.matcher(activityContent);
			while (matcher.find()) {
				Pattern pattern1 = Pattern.compile("^((https|http|ftp|rtsp|mms)?://)+");
				Matcher matcher1 = pattern1.matcher(matcher.group(1));
				if(!matcher1.find()){
					// 随机生成图片路径
					String randompath = "activityImage" +new Date().getTime()+ (int) (Math.random() * 100)+ "_bigger.jpg";
					String realpath = relPath + randompath;
					// 解码并保存图片
					Base64.saveImage(matcher.group(1), realpath);
					//压缩图片
					ImageUtil.saveMinPhoto(realpath, realpath.replace("_bigger", "_smaller"), 400, 0.9d);
					String stri="/"+SysConfig.PROJECT+SysConfig.picturePath+randompath.replace("_bigger", "_smaller");
					activityContent = activityContent.replace(matcher.group(1), stri);
					
					pictureModel.setPicturePath(randompath);
					pictureModel.setPictureName(randompath.substring(0,randompath.length()-4));
					pictureList.add(pictureModel);
					}
			}
			String activityTime = request.getParameter("activityTime");
			DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = fmt.parse(activityTime);
			activityModel.setActivityTime(date);
			activityModel.setActivityContent(activityContent);
			activityModel.setActivityName(request.getParameter("activityName"));
			String isIndex = request.getParameter("isIndex");
			activityModel.setIsIndex(Integer.valueOf(isIndex));
			String userId = request.getParameter("userId");
			activityModel.setUserId(Integer.valueOf(userId));
			int result = service.insert(activityModel,pictureList);
			json.put("errorID", 1);
			json.put("result", result);
			json.put("msg", "查询成功");
		} catch (Exception e) {
			json.put("result", "");
			json.put("msg", "查询失败");
		}
		logger.debug(json.toJSONString());
		return json;
	}
	
	/**
	 * 修改
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
	public JSONObject updateActivity(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		json.put("errorID", 0);
		try {
			ActivityModel activityModel = new ActivityModel();
			String id =  request.getParameter("id");
			activityModel.setId(Integer.valueOf(id));
			activityModel.setCreatetime(new Date());
			
			/*UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
				    .getAuthentication()
				    .getPrincipal();
			*/
			//定义上传路径---图片统一保存在  项目目录下面
			String relPath ="";
			relPath = request.getSession().getServletContext().getRealPath("")+SysConfig.picturePath;
			File webrootPath = new File(relPath);
			if (!webrootPath.isDirectory()) {
				webrootPath.mkdirs();
			}
			String activityContent=request.getParameter("activityContent");
			List<PictureModel> pictureList=new ArrayList<PictureModel>();//用于保存生成图片的路径
			PictureModel pictureModel = new PictureModel();
			pictureModel.setPictureType("activity");
			//pictureModel.setUserId(userDetails.getUsername());
			Pattern pattern = Pattern.compile("src=\"(.+?)\"");
			Matcher matcher = pattern.matcher(activityContent);
			while (matcher.find()) {
				//获取编辑内容中的src中的值
				String cropath=matcher.group(1);
				//包含data,表示添加了新图片
				if (cropath.indexOf("data") != -1) {
					// 随机生成资讯图片路径
					String randompath = "activityImage" + new Date().getTime()+ (int) (Math.random() * 100) + "_bigger.jpg";
					String realpath = relPath + randompath;
					// 解码并保存图片
					Base64.saveImage(cropath, realpath);
					//压缩图片
					ImageUtil.saveMinPhoto(realpath, realpath.replace("_bigger", "_smaller"), 400, 0.9d);
					String stri="/"+SysConfig.PROJECT+SysConfig.picturePath+randompath.replace("_bigger", "_smaller");
					activityContent = activityContent.replace(matcher.group(1), stri);
					
					pictureModel.setPicturePath(randompath);
					pictureModel.setPictureName(randompath.substring(0,randompath.length()-4));
					pictureList.add(pictureModel);
				}else{
					String stri=cropath.substring(cropath.lastIndexOf("/"));
					pictureModel.setPicturePath(stri);
					pictureModel.setPictureName(stri.substring(0,stri.length()-4));
					pictureList.add(pictureModel);
					//imageItemPath += stri+";";
					///boolean resrt=reNewsImgPath.contains(stri);
					//(resrt){
					//	reNewsImgPath = reNewsImgPath.replace(stri, "");
					//}
				}
			}
			String activityTime = request.getParameter("activityTime");
			DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = fmt.parse(activityTime);
			activityModel.setActivityTime(date);
			activityModel.setActivityContent(activityContent);
			activityModel.setActivityName(request.getParameter("activityName"));
			String isIndex = request.getParameter("isIndex");
			activityModel.setIsIndex(Integer.valueOf(isIndex));
			String userId = request.getParameter("userId");
			activityModel.setUserId(Integer.valueOf(userId));
			int result = service.updateByPrimaryKeySelective(activityModel);
			json.put("errorID", 1);
			json.put("result", result);
			json.put("msg", "查询成功");
		} catch (Exception e) {
			json.put("result", "");
			json.put("msg", "查询失败");
		}
		logger.debug(json.toJSONString());
		return json;
	}
}
