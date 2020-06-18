package com.nextop.metadata.repository;

import com.nextop.metadata.entity.Field;

import java.util.List;

/**
 * Description: 元数据属性Column的持久接口
 *
 * @author: eric.liang
 * @date: 6/1/20
 * @update:
 */

public interface FieldRepository extends Repository<Field> {

    int saveBatch(List<Field> fields);
}
