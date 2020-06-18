package com.nextop.metadata.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 6/11/20
 * @update:
 */
public class MetaClass implements Serializable, Cloneable {

    private Classification classification;
    private List<Field> fields;
}
