package cn.itcast.feign.faillback;

import cn.itcast.feign.clients.UserClient;
import feign.hystrix.FallbackFactory;

public class UserClientFallbackFactory  implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        return null;
    }
}
