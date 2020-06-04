package com.nextop.metadata.repository;

import java.io.Serializable;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 6/1/20
 * @update:
 */
public interface Repository<T extends Serializable> {

    int save(T t);

    T findById(Long id);
}
