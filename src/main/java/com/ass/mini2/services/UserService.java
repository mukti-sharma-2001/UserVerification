package com.ass.mini2.services;

import java.util.List;

import com.ass.mini2.entity.User;

public interface UserService {
	User createUser(User user);
	List<User> getUsers(String sortType, String sortOrder, int limit, int offset);
}
