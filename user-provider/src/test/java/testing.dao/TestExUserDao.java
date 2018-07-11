package testing.dao;


import one.yate.spring.cloud.provider.UserProviderApplication;
import one.yate.spring.cloud.provider.matedata.dao.mapper.ExUserMapper;
import one.yate.spring.cloud.provider.matedata.entity.ExUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserProviderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestExUserDao {
    @Resource
    ExUserMapper exUserDao;

    @Test
    public void test1() {
        ExUser e = this.exUserDao.getEntity(1);
        Assert.assertNotNull(e);
        ExUser e2 = this.exUserDao.findBy(e.username());
        Assert.assertEquals(e.username(), e2.username());
        int x = this.exUserDao.updateBy("mybatis", e2.id());
        Assert.assertNotEquals(x, 0);
    }
}
