package one.yate.spring.cloud.consumer.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import one.yate.spring.cloud.remote.api.IDemoRestService;
import one.yate.spring.cloud.remote.api.IMovieRestService;
import one.yate.spring.cloud.remote.api.IOrderRestService;
import one.yate.spring.cloud.remote.pojo.ErrorResult;
import one.yate.spring.cloud.remote.pojo.vo.MovieLockVo;
import one.yate.spring.cloud.remote.pojo.vo.OrderVo;
import one.yate.spring.cloud.remote.pojo.vo.UserVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderRestConsumer {

    @Resource
    IMovieRestService helloRestRomte;
    @Resource
    IDemoRestService demoRestRomte;
    @Resource
    IOrderRestService orderRestService;

    @Data
    public static class OrderDto {
        private Integer userId;
        private Integer movieId;
        private Integer nums;
    }

    @Data
    public static class PayOrderDto {
        private Integer userId;
        private Integer orderId;
    }


    @PostMapping("/order/create")
    public String createOrder(@RequestBody OrderVo vo) {

        UserVo user = demoRestRomte.test1(vo.getUserId());

        if (user == null) {
            return "user not exists";
        }

        try {
            MovieLockVo lock = helloRestRomte.lockTicket(vo.getMovieId(), vo.getTicketNums());
            if (lock != null && !lock.getSeatList().isEmpty()) {
                OrderVo orderVo = new OrderVo();
                orderVo.setUserId(user.getId());
                orderVo.setUserName(user.getUsername());
                orderVo.setMovieId(lock.getMovieId());
                orderVo.setMovieName(lock.getMovieName());
                orderVo.setLockSeats(lock.getSeatList().toString());
                orderVo.setTicketNums(vo.getTicketNums());
                orderVo.setTicketPrice(lock.getTicketPrice());

                Integer oid = orderRestService.createOrder(orderVo);

                return oid.toString();
            }

            return "try lock ticket error";
        } catch (ErrorResult e) {
            log.error(e.getMessage(), e);
            return "order create error";
        }

    }

    @PostMapping("/order/pay/{orderId}")
    public String payOrder(@PathVariable("orderId") Integer orderId) {
//        UserVo user = demoRestRomte.test1(userId);
//
//        if (user == null) {
//            return "user not exists";
//        }

        try {
            Integer flowId = this.orderRestService.payOrder(orderId);
            return flowId.toString();
        } catch (ErrorResult e) {
            log.error(e.getMessage(), e);
            return "order create error";
        }
    }
}
