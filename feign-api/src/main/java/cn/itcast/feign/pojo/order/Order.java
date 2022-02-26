package cn.itcast.feign.pojo.order;

import cn.itcast.feign.pojo.user.User;
import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;
    private User user;
}