package com.ass.mini2.helper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ass.mini2.entity.User;

@Component
public class AgeEvenFirstSorting implements UserSorting{

	@Override
	public List<User> sortUsers(List<User> users) {
		users.sort(Comparator.comparing(User::getAge, Comparator.comparingInt(age -> age % 2 == 0 ? 0 : 1)));
        return users;
	    
	}

}
