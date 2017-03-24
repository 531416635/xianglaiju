package com.yao.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.yao.model.FileInfo;
import com.yao.model.PhotoAlbum;
import com.yao.model.UserModel;
import com.yao.service.AlbumService;

/**
 * 与班级展示相关的内容
 * 
 * @author yaoyuxiao
 * @date 2016年9月2日 上午9:34:33
 */
@Controller
@RequestMapping("/web")
public class AlbumController {
	private static final Logger logger = LoggerFactory
			.getLogger(AlbumController.class);

	@Autowired
	private AlbumService albumService;

	/**
	 * 班级相册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toalbum")
	public String toalbum(Model model) {
		List<PhotoAlbum> photoAlbums = albumService.selectphotoalbum();
		int len=photoAlbums.size();
		Map<Integer, Object> nameMap =new HashMap<Integer, Object>();
		if(len>0){
			for (int i = 0; i < len; i++) {
				String str=photoAlbums.get(i).getAlbumname();
				if (!StringUtils.isEmpty(str)) {
					nameMap.put(photoAlbums.get(i).getId(), str);
				}
			}
		}
		model.addAttribute("photoAlbums", photoAlbums);
		model.addAttribute("nameMap", nameMap);
		return "website/album";
	}

	/**
	 * 班级相册
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@ResponseBody
	@RequestMapping(value = "/toalbumajax")
	public JSONObject toalbumajax(HttpServletRequest request,
			HttpServletResponse response,String albumtext,String albumvalue) throws IllegalStateException,
			IOException {
		/**
		 * 若上传的为相册图片，则通过下面处理为图片添加所属相册集
		 */
		if(!StringUtils.isEmpty(albumtext)){
			albumtext=new String(albumtext.getBytes("iso-8859-1"), "UTF-8");
		}
		if(!StringUtils.isEmpty(albumvalue)){
			albumvalue=new String(albumvalue.getBytes("iso-8859-1"), "UTF-8");
		}
		if(StringUtils.isEmpty(albumvalue)){
			if(!StringUtils.isEmpty(albumtext)){
				PhotoAlbum photoalbum1=albumService.selectphotoalbumbyname(albumtext);
				if (StringUtils.isEmpty(photoalbum1)) {
					PhotoAlbum photoalbum=new PhotoAlbum();
					photoalbum.setAlbumname(albumtext);
					photoalbum.setAlbumtime(new Date());
					albumService.insertalbum(photoalbum);
					albumvalue=photoalbum.getId()+"";
				}else{
					albumvalue=photoalbum1.getId()+"";
				}
			}
		}
		/**
		 * 判断用户是否登录或在线，并以此为文件添加上传人
		 */
		HttpSession session = request.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		FileInfo fileInfo = new FileInfo();
		fileInfo.setUploaduser(user.getId());
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
						fileInfo.setFiletype(houzhuiming);
						albumService.savefile(fileInfo);
						// 重命名上传后的文件名
						path = webrootPath + "/" + fileInfo.getId()
								+ houzhuiming;
						File localFile = new File(path);
						file.transferTo(localFile);
						fileInfo.setFilepath("/" + user.getId() + "/"
								+ fileInfo.getId() + houzhuiming);
						fileInfo.setUploadtime(new Date());
						fileInfo.setFilename(fileInfo.getId() + "");
						if(!StringUtils.isEmpty(albumvalue)){
							fileInfo.setAlbumid(Integer.parseInt(albumvalue));
						}
						PhotoAlbum photoalbums=albumService.selectphotoalbumbyid(Integer.parseInt(albumvalue));
						if(StringUtils.isEmpty(photoalbums.getAlbumpath())){
							photoalbums.setAlbumpath(fileInfo.getFilepath());
							albumService.updatealbum(photoalbums);
						}
						albumService.updatefile(fileInfo);
					}
				}
			}

		}
		return JSONObject
				.parseObject("{'event':'1', 'data':'2', 'previewId':'3', 'index':'4'}");
	}
	
	/**
	 * 系统文件下载操作
	 * @param path 数据库中存储的文件路径
	 * @param filename	新命名的文件名称
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/downloadfile")
	public ResponseEntity<byte[]> downloadfile(String path, String filename,HttpServletRequest request)
			throws IOException {
		String path1 = request.getServletContext().getRealPath("");//获取项目动态绝对路径 
		File file = new File(path1+path);
		HttpHeaders headers = new HttpHeaders();
		String fileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}
	
	@RequestMapping("/toalbumdetail")
	public String toAlbumDetail(Model model,String albumid){
		int albumid1=Integer.parseInt(albumid);
		List<FileInfo> images=albumService.selectfilebyalbumid(albumid1);
		model.addAttribute("images", images);
		return "website/albumDetail";
	}
}
