package one.yate.spring.cloud.remote.api;

import one.yate.spring.cloud.remote.config.FeignConfig;
import one.yate.spring.cloud.remote.hystrix.OrderRestServiceFallback;
import one.yate.spring.cloud.remote.pojo.ErrorResult;
import one.yate.spring.cloud.remote.pojo.vo.OrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(qualifier = "order-provider", name = "order-provider", fallback = OrderRestServiceFallback.class, configuration = {FeignConfig.class})
public interface IOrderRestService {
    @RequestMapping(value = "/order/create")
    public Integer createOrder(@RequestBody OrderVo vo) throws ErrorResult;

    @PostMapping(value = "/order/pay/{orderId}")
    public Integer payOrder(@PathVariable("orderId") Integer orderId) throws ErrorResult;
}
