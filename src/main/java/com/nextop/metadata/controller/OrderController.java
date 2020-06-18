package com.nextop.metadata.controller;

import com.nextop.metadata.entity.Order;
import com.nextop.metadata.service.OrderService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("")
    public int save(@RequestBody Order order) {
        return orderService.save(order);
    }
}
