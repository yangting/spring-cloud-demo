package one.yate.spring.cloud.provider.service;

import one.yate.spring.cloud.provider.metadata.entity.OrderInfo;
import one.yate.spring.cloud.remote.pojo.vo.OrderVo;


/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
public interface IOrderInfoService extends IBaseService<OrderInfo, Integer> {
    Integer createOrder(OrderVo vo);

    Integer payOrder(Integer orderId);

//    Integer refundOrder(Integer orderId);
}