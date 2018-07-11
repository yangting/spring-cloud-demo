package one.yate.spring.cloud.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfigBean implements WebMvcConfigurer {

    @Bean
    public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
        return new ResponseBodyWrapFactoryBean();
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        ResultHandlerExceptionResolver her = new ResultHandlerExceptionResolver(new JacksonConvertFactory());
        resolvers.add(her);
    }

}
