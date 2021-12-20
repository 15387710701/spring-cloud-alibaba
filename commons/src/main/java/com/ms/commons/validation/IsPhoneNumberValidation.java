package com.ms.commons.validation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ConstraintValidator 的参数1 是注解 ,参数二 校验的数据的类型
 */
public class IsPhoneNumberValidation implements ConstraintValidator<IsPhoneNumber, String> {
    private boolean required = false;

    /**
     * 校验逻辑
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //如果是必须穿的
        if (required) {
            return value.length() == 11;
        } else {
            //不是必须传
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return value.length() == 11;
            }
        }
    }

    /**
     * 初始化-
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IsPhoneNumber constraintAnnotation) {
        required = constraintAnnotation.quired();
    }
}
