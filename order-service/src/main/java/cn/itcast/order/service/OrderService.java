package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.order.Order;
import cn.itcast.feign.pojo.user.User;
import cn.itcast.order.mapper.OrderMapper;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    //    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        String url = "http://userservice/user/"+ order.getUserId();
//        User user = restTemplate.getForObject(url, User.class);
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        User userById = userClient.getUserById(order.getUserId());
        order.setUser(userById);
        // 4.返回
        return order;
    }


    public List<Order> getByUserId(Long userId) {
        List<Order> result = orderMapper.getByUserId(userId);
        User userById = userClient.getUserById(userId);
        for (Order order : result) {
            order.setUser(userById);
        }
        return result;
    }

    @SentinelResource("goods")
    public void queryGoods(){
        System.err.println("查询商品");
    }
}
