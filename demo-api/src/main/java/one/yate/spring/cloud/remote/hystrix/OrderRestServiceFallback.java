package one.yate.spring.cloud.remote.hystrix;

import lombok.extern.slf4j.Slf4j;
import one.yate.spring.cloud.remote.api.IOrderRestService;
import one.yate.spring.cloud.remote.pojo.ErrorResult;
import one.yate.spring.cloud.remote.pojo.vo.OrderVo;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderRestServiceFallback implements IOrderRestService {

    @Override
    public Integer createOrder(OrderVo vo) throws ErrorResult {
        throw new ErrorResult(-1, "order");
    }

    @Override
    public Integer payOrder(Integer orderId) throws ErrorResult {
        throw new ErrorResult(-1, "order");
    }
}
