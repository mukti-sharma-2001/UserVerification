package com.ass.mini2.entity;

import java.util.List;

public class UserListResponse {
	private List<User> users;
    private PageInfo pageInfo;

    public UserListResponse(List<User> users, PageInfo pageInfo) {
        this.users = users;
        this.pageInfo = pageInfo;
    }

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Override
	public String toString() {
		return "UserListResponse [users=" + users + ", pageInfo=" + pageInfo + "]";
	}
    
}
