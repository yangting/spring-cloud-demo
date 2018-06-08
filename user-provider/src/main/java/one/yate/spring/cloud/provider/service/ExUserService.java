package one.yate.spring.cloud.provider.service;


import one.yate.spring.cloud.provider.matedata.entity.ExUser;

/**
 * @author yangting
 * @date 2018/5/16
 * TODO
 */
public interface ExUserService {
    ExUser findBy(Integer id);

    ExUser findBy(String username);

    public int updateBy(String username, Integer id);
}
