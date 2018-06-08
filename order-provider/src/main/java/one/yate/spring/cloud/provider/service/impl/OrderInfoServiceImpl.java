package one.yate.spring.cloud.provider.service.impl;

import one.yate.spring.cloud.provider.metadata.dao.IBaseMapperDao;
import one.yate.spring.cloud.provider.metadata.dao.mapper.OrderFlowMapper;
import one.yate.spring.cloud.provider.metadata.dao.mapper.OrderInfoMapper;
import one.yate.spring.cloud.provider.metadata.entity.OrderFlow;
import one.yate.spring.cloud.provider.metadata.entity.OrderInfo;
import one.yate.spring.cloud.provider.service.IOrderInfoService;
import one.yate.spring.cloud.remote.pojo.vo.OrderVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
@Service
public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfo, Integer> implements IOrderInfoService {
    @Resource
    private OrderInfoMapper mapper;

    @Resource
    private OrderFlowMapper flowMapper;

    @Transactional
    @Override
    public Integer createOrder(OrderVo vo) {
        OrderInfo e = new OrderInfo();
        e.setUserId(vo.getUserId());
        e.setUserName(vo.getUserName());
        e.setMovieId(vo.getMovieId());
        e.setMovieName(vo.getMovieName());
        e.setLockSeats(vo.getLockSeats());
        e.setTicketNums(vo.getTicketNums());
        e.setTicketPrice(vo.getTicketPrice());
        mapper.add(e);

        OrderFlow flow = new OrderFlow();
        flow.setOrderId(e.getId());
        flow.setOrderStatus(1);
        flow.setOrderChain(1);
        flowMapper.add(flow);

        return e.getId();
    }

    @Transactional
    @Override
    public Integer payOrder(Integer orderId) {
        OrderFlow e = flowMapper.getRecently(orderId);
        if (e == null) {
            throw new NullPointerException();
        }

        if (e.getOrderStatus().intValue() == 2) {
            return e.getId();
        }

        OrderFlow flow = new OrderFlow();
        flow.setOrderId(e.getOrderId());
        flow.setOrderStatus(2);
        flow.setOrderChain(e.getOrderChain() + 1);
        flowMapper.add(flow);

        return flow.getId();
    }

    protected IBaseMapperDao<OrderInfo, Integer> getMapperDao() {
        return mapper;
    }

}