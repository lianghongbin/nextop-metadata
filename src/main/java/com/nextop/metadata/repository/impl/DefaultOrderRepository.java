package com.nextop.metadata.repository.impl;

import com.nextop.metadata.entity.Order;
import com.nextop.metadata.mapper.OrderMapper;
import com.nextop.metadata.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 6/1/20
 * @update:
 */
@Repository
public class DefaultOrderRepository implements OrderRepository {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public int save(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public Order findById(Long id) {
        return orderMapper.selectById(id);
    }
}
