package com.nextop.metadata.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 5/28/20
 * @update:
 */
@TableName("nextop_order")
public class Order {

    private Long id;
    private String name;

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
}
