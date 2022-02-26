package cn.itcast.feign.clients;

import cn.itcast.feign.config.UserFeignConfig;
import cn.itcast.feign.pojo.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userservice",configuration = UserFeignConfig.class)
public interface UserClient {
    @GetMapping(value = "/user/{userId}")
    User getUserById(@PathVariable("userId") Long userId);
}
