package cn.itcast.order.web;

import cn.itcast.feign.pojo.order.Order;
import cn.itcast.order.service.OrderService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;

    @SentinelResource("hot")
    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }

    @GetMapping("getByUserId/{userId}")
    public List<Order> getByUserId(@PathVariable("userId")Long userId){
        List<Order> result = orderService.getByUserId(userId);
        return result;
    }

    @GetMapping("/query")
    public String queryOrder() {
        orderService.queryGoods();
        return "查询订单成功";
    }

    @GetMapping("/update")
    public String updateOrder() {
        orderService.queryGoods();
        return "更新订单成功";
    }

    @GetMapping("/save")
    public String saveOrder() {
        // 查询商品
        orderService.queryGoods();
        // 查询订单
        System.err.println("新增订单");
        return "新增订单成功";
    }




}
