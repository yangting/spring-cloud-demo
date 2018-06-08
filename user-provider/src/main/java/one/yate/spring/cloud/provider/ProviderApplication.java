package one.yate.spring.cloud.provider;

import lombok.extern.slf4j.Slf4j;
import one.yate.spring.cloud.provider.matedata.dao.IBaseMapperDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@MapperScan(basePackages = "one.yate.spring.cloud.provider.matedata.dao.mapper", markerInterface = IBaseMapperDao.class)
@ComponentScan(basePackages = "one.yate.spring.cloud.**")
@SpringBootApplication
@EnableEurekaClient
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
