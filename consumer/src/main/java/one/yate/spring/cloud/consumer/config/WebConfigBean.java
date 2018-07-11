package one.yate.spring.cloud.consumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfigBean implements WebMvcConfigurer {

//    @Bean
//    public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
//        return new ResponseBodyWrapFactoryBean();
//    }
//
//    @Override
//    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
//        ResultHandlerExceptionResolver her = new ResultHandlerExceptionResolver(new JacksonConvertFactory());
//        resolvers.add(her);
//    }

    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(List<HttpMessageConverter<?>> converters, ObjectMapper om) {
        if(converters==null)
            converters = new ArrayList<>();
        converters.add(new ResultHttpMessageConverter(om));
        return new HttpMessageConverters(converters);
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new ResultHttpMessageConverter());
//    }

//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new ResultHttpMessageConverter());
//    }
}
