package com.ass.mini2.helper;

import org.springframework.stereotype.Component;

import com.ass.mini2.services.Validator;

@Component
public class EnglishAlphabetValidator implements Validator{
	 private EnglishAlphabetValidator() {
	    }

	    
	    private static class SingletonHolder {
	        private static final EnglishAlphabetValidator INSTANCE = new EnglishAlphabetValidator();
	    }
	    public static EnglishAlphabetValidator getInstance() {
	        return SingletonHolder.INSTANCE;
	    }

	@Override
	public boolean isValid(Object parameter) {
		if (parameter instanceof String) {
	        String value = (String) parameter;
	        return value.matches("^[a-zA-Z]*$");
	    }
		return false;
	}
}
