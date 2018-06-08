package one.yate.spring.cloud.provider.matedata.dao.mapper;

import one.yate.spring.cloud.provider.matedata.dao.IBaseMapperDao;
import one.yate.spring.cloud.provider.matedata.entity.ExUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 本段代码由sql2java自动生成.
 * https://github.com/yangting/sql2java
 *
 * @author Yate
 */
@Repository
public interface ExUserMapper extends IBaseMapperDao<ExUser, Integer> {
    ExUser findBy(@Param("username") String username);

    int updateBy(@Param("username") String username, @Param("id") Integer id);
}
