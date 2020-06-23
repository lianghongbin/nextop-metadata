package com.nextop.metadata.entity;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 6/23/20
 * @update:
 */

public enum RelationType {

    INCLUDE(0),
    DEPEND(1),
    ASSOCIATE(2);

    private int value;

    private RelationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
