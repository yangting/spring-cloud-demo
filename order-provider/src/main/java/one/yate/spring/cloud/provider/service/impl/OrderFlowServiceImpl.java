package one.yate.spring.cloud.provider.service.impl;

import one.yate.spring.cloud.provider.metadata.dao.IBaseMapperDao;
import one.yate.spring.cloud.provider.metadata.dao.mapper.OrderFlowMapper;
import one.yate.spring.cloud.provider.metadata.entity.OrderFlow;
import one.yate.spring.cloud.provider.service.IOrderFlowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
@Service
public class OrderFlowServiceImpl extends BaseServiceImpl<OrderFlow, Integer> implements IOrderFlowService {
    @Resource
    private OrderFlowMapper mapper;

    protected IBaseMapperDao<OrderFlow, Integer> getMapperDao() {
        return mapper;
    }
}