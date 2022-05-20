package cn.itcast.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        MultiValueMap<String, String> queryParams = request.getQueryParams();
//        String authorization = queryParams.getFirst("authorization");
//        if("admin".equalsIgnoreCase(authorization)){
//            return chain.filter(exchange);
//        }
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
