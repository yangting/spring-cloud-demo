package one.yate.spring.cloud.provider.metadata.dao.mapper;

import one.yate.spring.cloud.provider.metadata.dao.IBaseMapperDao;
import one.yate.spring.cloud.provider.metadata.entity.OrderFlow;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
@Repository
public interface OrderFlowMapper extends IBaseMapperDao<OrderFlow, Integer> {
    OrderFlow getRecently(@Param("orderId") Integer orderId);
}
