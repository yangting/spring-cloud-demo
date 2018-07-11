package one.yate.spring.cloud.provider.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

public class ResponseBodyWrapFactoryBean implements InitializingBean {
    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @Override
    public void afterPropertiesSet() {
        //UnmodifiableRandomAccessList
        List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList(returnValueHandlers);
        decorateHandlers(handlers);
        adapter.setReturnValueHandlers(handlers);
    }


    private void decorateHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        for (HandlerMethodReturnValueHandler handler : handlers) {
            if (handler instanceof RequestResponseBodyMethodProcessor) {
                ResultHandlerMethodReturnValueHandler decorator = new ResultHandlerMethodReturnValueHandler(handler);
                int index = handlers.indexOf(handler);
                handlers.set(index, decorator);
                break;
            }
        }
    }

    class ResultHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
        private final HandlerMethodReturnValueHandler delegate;

        public ResultHandlerMethodReturnValueHandler(HandlerMethodReturnValueHandler delegate) {
            this.delegate = delegate;
        }

        @Override
        public boolean supportsReturnType(MethodParameter returnType) {
            return delegate.supportsReturnType(returnType);
        }

        @Override
        public void handleReturnValue(Object returnValue,
                                      MethodParameter returnType,
                                      ModelAndViewContainer mavContainer,
                                      NativeWebRequest webRequest) throws Exception {
            if (returnValue != null && !(returnValue instanceof ResBean)) {
                ResBean result = ResBean.success(returnValue);
                delegate.handleReturnValue(result, returnType, mavContainer, webRequest);
            }else{
                delegate.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            }
        }
    }
}
