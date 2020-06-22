package com.nextop.metadata.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nextop.metadata.entity.Classification;
import com.nextop.metadata.mapper.ClassificationMapper;
import com.nextop.metadata.repository.ClassificationRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 6/1/20
 * @update:
 */
@Component
public class DefaultClassificationRepository implements ClassificationRepository {

    @Resource
    private ClassificationMapper classificationMapper;

    @Override
    public int save(Classification classification) {
        return classificationMapper.insert(classification);
    }

    @Override
    public int saveNotExist(Classification classification) {
        QueryWrapper<Classification> queryWrapper = new QueryWrapper<Classification>();
        queryWrapper.lambda().eq(Classification::getName, classification.getName());
        Classification cf = classificationMapper.selectOne(queryWrapper);
        if (cf != null) {
            return 0;
        }

        return classificationMapper.insert(classification);
    }

    @Override
    public Classification findById(Long id) {
        return classificationMapper.selectById(id);
    }

    @Override
    public Classification findByName(String name) {
        QueryWrapper<Classification> queryWrapper = new QueryWrapper<Classification>();
        queryWrapper.lambda().eq(Classification::getName, name);
        return classificationMapper.selectOne(queryWrapper);
    }
}
