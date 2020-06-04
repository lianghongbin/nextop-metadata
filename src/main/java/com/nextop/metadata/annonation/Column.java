package com.nextop.metadata.annonation;

import com.baomidou.mybatisplus.annotation.TableField;

import java.lang.annotation.*;


@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
}
