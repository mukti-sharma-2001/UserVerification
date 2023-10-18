package com.ass.mini2.helper;

import org.springframework.stereotype.Component;

import com.ass.mini2.util.AppConstants;

@Component
public class UserSortingFactory {
	public UserSorting getSortingStrategy(String sortType, String sortOrder) {
        if (AppConstants.AGE.equalsIgnoreCase(sortType)) {
        	System.out.println("age");
            if (AppConstants.EVEN.equalsIgnoreCase(sortOrder)) {
            	System.out.println("ageeven");
                return new AgeEvenFirstSorting();
            } else if (AppConstants.ODD.equalsIgnoreCase(sortOrder)) {
            	System.out.println("ageo");
                return new AgeOddFirstSorting();
            }
        } else if (AppConstants.NAME.equalsIgnoreCase(sortType)) {
        	System.out.println("name");
            if (AppConstants.ODD.equalsIgnoreCase(sortOrder)) {
            	System.out.println("nameodd");
                return new NameOddFirstSorting();
            } else if (AppConstants.EVEN.equalsIgnoreCase(sortOrder)) {
            	System.out.println("nameev");
                return new NameEvenFirstSorting();
            }
        }
		return new NameEvenFirstSorting();
	}
}
