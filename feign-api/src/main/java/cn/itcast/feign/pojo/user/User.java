package cn.itcast.feign.pojo.user;

import cn.itcast.feign.pojo.order.Order;
import lombok.Data;

import java.util.List;

@Data
public class User {
    private Long id;
    private String username;
    private String address;
    private List<Order> orders;
}
