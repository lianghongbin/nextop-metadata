package com.nextop.metadata.repository.impl;

import com.nextop.metadata.entity.Field;
import com.nextop.metadata.mapper.FieldMapper;
import com.nextop.metadata.repository.FieldRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 6/1/20
 * @update:
 */
@Component
public class DefaultFieldRepository implements FieldRepository {

    @Resource
    private FieldMapper fieldMapper;

    @Override
    public int save(Field field) {
        return fieldMapper.insert(field);
    }

    @Override
    public Field findById(Long id) {
        return fieldMapper.selectById(id);
    }

    @Override
    public int saveBatch(List<Field> fields) {
        if (fields == null) {
            return 0;
        }

        fields.forEach(item -> {
            fieldMapper.insert(item);
        });

        return fields.size();
    }
}
