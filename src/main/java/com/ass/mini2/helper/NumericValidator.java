package com.ass.mini2.helper;

import org.springframework.stereotype.Component;

import com.ass.mini2.services.Validator;

@Component
public class NumericValidator implements Validator {

    private NumericValidator() {
    }

    
    private static class SingletonHolder {
        private static final NumericValidator INSTANCE = new NumericValidator();
    }

    public static NumericValidator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public boolean isValid(Object parameter) {
        if (parameter instanceof Integer) {
            int value = (Integer) parameter;
            return value >= 1 && value <= 5;
        } else if (parameter instanceof Long) {
            long value = (Long) parameter;
            return value >= 1 && value <= 5;
        }
        return false;
    }
}
