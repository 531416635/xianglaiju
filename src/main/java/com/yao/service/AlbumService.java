package com.yao.service;

import java.util.List;

import com.yao.model.FileInfo;
import com.yao.model.PhotoAlbum;

public interface AlbumService {

	/**
	 * 保存文件信息
	 * @param fileInfo
	 * @return
	 */
	public int savefile(FileInfo fileInfo);
	
	/**
	 * 更新文件信息
	 * @param fileInfo
	 */
	void updatefile(FileInfo fileInfo);
	
	/**
	 * 查询文件信息
	 * @return
	 */
	List<FileInfo> selectfile();
	
	/**
	 * 根据相册ID，查询图片文件信息
	 * @param albumid
	 * @return
	 */
	List<FileInfo> selectfilebyalbumid(int albumid);
	
	/**
	 * 查询相册信息
	 * @return
	 */
	List<PhotoAlbum> selectphotoalbum();
	
	/**
	 * 插入相册信息并返回ID
	 * @param photoalbum
	 * @return
	 */
	int insertalbum(PhotoAlbum photoalbum);
	
	/**
	 * 更新相册信息
	 * @param photoalbum
	 * @return
	 */
	void updatealbum(PhotoAlbum photoalbum);
	
	/**
	 * 根据id查询相册信息
	 * @return
	 */
	PhotoAlbum selectphotoalbumbyid(int id);
	
	/**
	 * 根据name查询相册信息
	 * @return
	 */
	PhotoAlbum selectphotoalbumbyname(String albumname);
}
