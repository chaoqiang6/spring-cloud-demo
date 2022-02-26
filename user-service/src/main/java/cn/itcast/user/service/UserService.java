package cn.itcast.user.service;

import cn.itcast.feign.clients.OrderClient;
import cn.itcast.feign.pojo.order.Order;
import cn.itcast.feign.pojo.user.User;
import cn.itcast.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderClient orderClient;

    public User queryById(Long id) {
        return userMapper.findById(id);
    }


    public User getUserWithOrderByUserId(Long userId) {
        User byId = userMapper.findById(userId);
        List<Order> orderByUserId = orderClient.getOrderByUserId(byId.getId());
        byId.setOrders(orderByUserId);
        return byId;
    }
}