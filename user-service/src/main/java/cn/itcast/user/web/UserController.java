package cn.itcast.user.web;

import cn.itcast.feign.pojo.user.User;
import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

//    @Value("${pattern.dateformat}")
//    private String dateformat;
    @Autowired
    private PatternProperties patternProperties;

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }

    @GetMapping("now")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat(), Locale.CHINA));
    }

    @GetMapping("prop")
    public PatternProperties prop(){
        return patternProperties;
    }

    @GetMapping("/getUserWithOrderByUserId/{id}")
    public User getUserWithOrder(@PathVariable("id") Long userId){
        return userService.getUserWithOrderByUserId(userId);
    }

}
