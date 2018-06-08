package one.yate.spring.cloud.gateway.config;

import com.netflix.zuul.ZuulFilter;
import one.yate.spring.cloud.gateway.filter.ErrorRequestLogFilter;
import one.yate.spring.cloud.gateway.filter.PostRequestLogFilter;
import one.yate.spring.cloud.gateway.filter.PreRequestLogFilter;
import one.yate.spring.cloud.gateway.filter.RouteRequestLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {
    @Bean
    public ZuulFilter preRequestLogFilter() {
        return new PreRequestLogFilter();
    }

    @Bean
    public ZuulFilter routeRequestLogFilter() {
        return new RouteRequestLogFilter();
    }

    @Bean
    public ZuulFilter postRequestLogFilter() {
        return new PostRequestLogFilter();
    }

    @Bean
    public ZuulFilter errorRequestLogFilter() {
        return new ErrorRequestLogFilter();
    }
}
