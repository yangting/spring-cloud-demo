package one.yate.spring.cloud.provider.service.impl;

import one.yate.spring.cloud.provider.matedata.dao.mapper.ExUserMapper;
import one.yate.spring.cloud.provider.matedata.entity.ExUser;
import one.yate.spring.cloud.provider.service.ExUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangting
 * @date 2018/5/16
 * TODO
 */
@Service
public class ExUserServiceImpl implements ExUserService {
    @Resource
    private ExUserMapper exUserMapper;

    public ExUser findBy(Integer id) {
        ExUser e = exUserMapper.getEntity(id);
        return e;
    }

    @Override
    public ExUser findBy(String username) {
        ExUser e = exUserMapper.findBy(username);
        return e;
    }

    @Override
    public int updateBy(String username, Integer id) {
        return this.exUserMapper.updateBy(username, id);
    }
}
