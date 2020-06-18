package com.nextop.metadata.service;

import com.nextop.metadata.entity.Order;
import com.nextop.metadata.mapper.OrderMapper;
import com.nextop.metadata.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 租户信息 服务实现类
 *
 * @author majiangping
 * @since 2020-04-09
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Override
    public Order find(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public int save(Order order) {
        return orderRepository.save(order);
    }
}
