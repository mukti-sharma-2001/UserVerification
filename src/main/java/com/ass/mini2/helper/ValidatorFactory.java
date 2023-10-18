package com.ass.mini2.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ass.mini2.services.Validator;

@Component
public class ValidatorFactory {
    private final NumericValidator numericValidator;
    private final EnglishAlphabetValidator englishAlphabetValidator;

    @Autowired
    public ValidatorFactory(NumericValidator numericValidator, EnglishAlphabetValidator englishAlphabetValidator) {
        this.numericValidator = numericValidator;
        this.englishAlphabetValidator = englishAlphabetValidator;
    }

    public Validator getValidator(Object parameter) {
        if (parameter instanceof Integer || parameter instanceof Long) {
            return numericValidator;
        } else if (parameter instanceof String) {
            return englishAlphabetValidator;
        } else {
            throw new IllegalArgumentException("Unsupported parameter type");
        }
    }
}
