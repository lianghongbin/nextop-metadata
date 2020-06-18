package com.nextop.metadata.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.nextop.metadata.annonation.Classifier;
import com.nextop.metadata.annonation.Column;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 5/28/20
 * @update:
 */
@TableName("nextop_order")
@Classifier
public class Order implements Serializable {

    private Long id;
    private String name;
    @Column
    @TableField(exist = false)
    private List<Field> fields;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
