package com.yao.model;

import java.io.Serializable;

public class RoleMenu implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8491132985078852655L;

	private Integer id;

    private Integer menuid;

    private Integer roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}