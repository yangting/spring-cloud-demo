package one.yate.spring.cloud.gateway.config;

import com.netflix.zuul.ZuulFilter;
import one.yate.spring.cloud.gateway.filter.ErrorRequestLogFilter;
import one.yate.spring.cloud.gateway.filter.PostRequestLogFilter;
import one.yate.spring.cloud.gateway.filter.PreRequestLogFilter;
import one.yate.spring.cloud.gateway.filter.RouteRequestLogFilter;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.discovery.DiscoveryClientRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.cloud.netflix.zuul.filters.discovery.ServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {
    @Bean
    public DiscoveryClientRouteLocator discoveryClientRouteLocator(DiscoveryClient discovery, ServerProperties server, ZuulProperties zuulProperties, ServiceInstance localServiceInstance) {
        ServiceRouteMapper serviceRouteMapper = new PatternServiceRouteMapper("(?<name>.*)-(?<type>.*)-(?<version>v.*$)", "${name}/${version}");
        DiscoveryClientRouteLocator routeLocator = new DiscoveryClientRouteLocator(server.getServlet().getServletPrefix(), discovery, zuulProperties, serviceRouteMapper, localServiceInstance);
        return routeLocator;
    }

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
