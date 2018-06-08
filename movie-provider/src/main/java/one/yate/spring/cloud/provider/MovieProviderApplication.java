package one.yate.spring.cloud.provider;

import lombok.extern.slf4j.Slf4j;
import one.yate.spring.cloud.provider.metadata.dao.IBaseMapperDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@MapperScan(basePackages = "one.yate.spring.cloud.provider.metadata.dao.mapper", markerInterface = IBaseMapperDao.class)
@ComponentScan(basePackages = "one.yate.spring.cloud.**")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaServer
public class MovieProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieProviderApplication.class, args);
    }
}
