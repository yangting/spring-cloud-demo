package one.yate.spring.cloud.provider.controller;

import lombok.extern.slf4j.Slf4j;
import one.yate.spring.cloud.provider.service.IOrderInfoService;
import one.yate.spring.cloud.remote.pojo.vo.OrderVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangting
 * @date 2018/4/10
 * TODO
 */
@Slf4j
@RestController
public class OrderRestProvider {
    @Resource
    private IOrderInfoService infoService;

    @RequestMapping(value = "/order/create")
    public Integer createOrder(@RequestBody OrderVo vo) {
        log.info("createOrder paramter : id = {}", vo);
        return this.infoService.createOrder(vo);
    }

    @PostMapping(value = "/order/pay/{orderId}")
    public Integer payOrder(@PathVariable("orderId") Integer orderId) {
        log.info("payOrder paramter = {}", orderId);
        return this.infoService.payOrder(orderId);
    }
}
