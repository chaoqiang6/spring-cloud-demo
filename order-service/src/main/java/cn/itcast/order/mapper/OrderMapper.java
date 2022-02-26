package cn.itcast.order.mapper;

import cn.itcast.feign.pojo.order.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper {

    @Select("select * from tb_order where id = #{id}")
    Order findById(Long id);

    @Select("select * from tb_order where user_id = #{userId}")
    List<Order> getByUserId(Long userId);
}
