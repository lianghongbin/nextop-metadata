package com.nextop.metadata.order.service;

import com.nextop.metadata.entity.Order;
import com.nextop.metadata.order.mapper.OrderMapper;
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
    private OrderMapper orderMapper;


    @Override
    public Order find(Long id) {
        return orderMapper.selectById(id);
    }
}
