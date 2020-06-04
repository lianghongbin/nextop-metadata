package com.nextop.metadata.repository.impl;

import com.nextop.metadata.entity.Field;
import com.nextop.metadata.repository.ColumnRepository;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 6/1/20
 * @update:
 */
@Component
public class DefaultColumnRepository implements ColumnRepository {
    @Override
    public int save(Field column) {
        return 0;
    }

    @Override
    public Field findById(Long id) {
        return null;
    }
}
