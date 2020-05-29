package com.nextop.metadata.order.controller;

import com.nextop.metadata.entity.Order;
import com.nextop.metadata.order.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 租户信息 前端控制器
 *
 * @author majiangping
 * @since 2020-04-09
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @GetMapping("")
    public Order find(@RequestParam() Long id) {

        return orderService.find(id);
    }
}
