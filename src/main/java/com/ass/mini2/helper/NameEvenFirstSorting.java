package com.ass.mini2.helper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ass.mini2.entity.User;
@Component
public class NameEvenFirstSorting implements UserSorting{

	@Override
	public List<User> sortUsers(List<User> users) {
		return users.stream()
	            .sorted(Comparator.comparing(User::getAge, Comparator.reverseOrder())
	                .thenComparing(User::getName))
	            .collect(Collectors.toList());
	}

}
