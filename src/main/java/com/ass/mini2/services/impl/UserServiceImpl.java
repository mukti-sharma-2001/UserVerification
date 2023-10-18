package com.ass.mini2.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.mini2.entity.User;
import com.ass.mini2.helper.UserSorting;
import com.ass.mini2.helper.UserSortingFactory;
import com.ass.mini2.repo.UserRepo;
import com.ass.mini2.services.UserService;
import com.ass.mini2.util.AppConstants;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserSortingFactory sortingFactory;
	@Override
	public User createUser(User user) {
		User savedUser=userRepo.save(user);
		return savedUser;
	}
	    @Override
	    public List<User> getUsers(String sortType, String sortOrder, int limit, int offset) {
	        // Sort users based on sortType and sortOrder
	        List<User> sortedUsers = sortUsers(sortType, sortOrder);

	        // Apply pagination
	        List<User> paginatedUsers = paginateUsers(sortedUsers, limit, offset);

	        return paginatedUsers;
	    }

	    private List<User> sortUsers(String sortType, String sortOrder) {
	    	List<User> users = userRepo.findAll();

	    	        // Select the sorting strategy based on SortType and SortOrder
	    	        UserSorting sortingStrategy = sortingFactory.getSortingStrategy(sortType, sortOrder);

	    	        // Use the selected strategy to sort the users
	    	        return sortingStrategy.sortUsers(users);
	    }

	    private List<User> paginateUsers(List<User> users, int limit, int offset) {
	        int startIndex = Math.min(offset, users.size());
	        int endIndex = Math.min(offset + limit, users.size());

	        if (startIndex > endIndex) {
	            startIndex = endIndex;
	        }

	        return users.subList(startIndex, endIndex);
	    }
	

}
