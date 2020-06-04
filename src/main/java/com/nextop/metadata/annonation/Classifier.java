package com.nextop.metadata.annonation;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.lang.annotation.*;


@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Classifier {

    public String name() default "";
}
