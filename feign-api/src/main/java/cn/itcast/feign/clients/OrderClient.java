package cn.itcast.feign.clients;

import cn.itcast.feign.pojo.order.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "orderservice")
public interface OrderClient {
    @GetMapping("/order/getByUserId/{userId}")
    List<Order> getOrderByUserId(@PathVariable("userId")Long userId);
}
