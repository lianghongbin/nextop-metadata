package com.nextop.metadata.repository;

import com.nextop.metadata.entity.Classification;

/**
 * Description: 元数据类别Classification类的持久化接口
 *
 * @author: eric.liang
 * @date: 6/1/20
 * @update:
 */

public interface ClassificationRepository extends Repository<Classification> {

    int saveNotExist(Classification classification);

    Classification findByName(String name);
}
