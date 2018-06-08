package one.yate.spring.cloud.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import zipkin2.Span;
import zipkin2.reporter.Reporter;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "one.yate.spring.cloud.remote.**")
@ComponentScan(basePackages = "one.yate.spring.cloud.**")
@EnableAsync
@EnableCircuitBreaker
public class ConsumerApplication {

    @Bean
    @ConditionalOnProperty(value = "sample.zipkin.enabled", havingValue = "false")
    public Reporter<Span> spanReporter() {
        return Reporter.CONSOLE;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
