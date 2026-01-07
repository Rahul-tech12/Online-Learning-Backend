package com.project.learning.validation;

import com.project.learning.Dto.Request.RegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterRequest> {

    @Override
    public boolean isValid(RegisterRequest value, ConstraintValidatorContext context){
        if(value==null) return true;
        String password=value.getPassword();
        String confirmPw=value.getConfirmPassword();
        if (password==null || confirmPw==null){
            return false;
        }
        return password.equals(confirmPw);
    }
}
