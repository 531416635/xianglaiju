package com.yao.model;

import java.util.Date;

public class PhotoAlbum {
    private Integer id;

    private String albumname;

    private String albumpath;

    private Date albumtime;

    private String param1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname == null ? null : albumname.trim();
    }

    public String getAlbumpath() {
        return albumpath;
    }

    public void setAlbumpath(String albumpath) {
        this.albumpath = albumpath == null ? null : albumpath.trim();
    }

    public Date getAlbumtime() {
        return albumtime;
    }

    public void setAlbumtime(Date albumtime) {
        this.albumtime = albumtime;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1 == null ? null : param1.trim();
    }
}