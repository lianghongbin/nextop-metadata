package com.nextop.metadata.repository.impl;

import com.nextop.metadata.entity.Classification;
import com.nextop.metadata.repository.ClassificationRepository;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 6/1/20
 * @update:
 */
@Component
public class DefaultClassificationRepository implements ClassificationRepository {
    @Override
    public int save(Classification classification) {
        return 0;
    }

    @Override
    public Classification findById(Long id) {
        return null;
    }
}
