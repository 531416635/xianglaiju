package com.yao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.dao.AlbumDao;
import com.yao.model.FileInfo;
import com.yao.model.PhotoAlbum;
import com.yao.service.AlbumService;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService{

	@Autowired
	private AlbumDao albumDao;
	
	@Override
	public int savefile(FileInfo fileInfo) {
		// TODO Auto-generated method stub
		return albumDao.savefile(fileInfo);
	}

	@Override
	public void updatefile(FileInfo fileInfo) {
		// TODO Auto-generated method stub
		albumDao.updatefile(fileInfo);
	}

	@Override
	public List<FileInfo> selectfile() {
		// TODO Auto-generated method stub
		return albumDao.selectfile();
	}

	@Override
	public List<PhotoAlbum> selectphotoalbum() {
		// TODO Auto-generated method stub
		return albumDao.selectphotoalbum();
	}

	@Override
	public int insertalbum(PhotoAlbum photoalbum) {
		// TODO Auto-generated method stub
		return albumDao.insertalbum(photoalbum);
	}

	@Override
	public void updatealbum(PhotoAlbum photoalbum) {
		// TODO Auto-generated method stub
		albumDao.updatealbum(photoalbum);
	}

	@Override
	public PhotoAlbum selectphotoalbumbyid(int id) {
		// TODO Auto-generated method stub
		return albumDao.selectphotoalbumbyid(id);
	}

	@Override
	public PhotoAlbum selectphotoalbumbyname(String albumname) {
		// TODO Auto-generated method stub
		return albumDao.selectphotoalbumbyname(albumname);
	}

	@Override
	public List<FileInfo> selectfilebyalbumid(int albumid) {
		// TODO Auto-generated method stub
		return albumDao.selectfilebyalbumid(albumid);
	}

}
