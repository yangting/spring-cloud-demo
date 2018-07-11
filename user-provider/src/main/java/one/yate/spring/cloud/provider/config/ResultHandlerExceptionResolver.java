package one.yate.spring.cloud.provider.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResultHandlerExceptionResolver implements HandlerExceptionResolver {

    protected final JsonConvertFactory factory;

    public ResultHandlerExceptionResolver(JsonConvertFactory f) {
        this.factory = f != null ? f : new JacksonConvertFactory();
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isBlank(response.getContentType()) ||
                StringUtils.indexOf(response.getContentType(), MediaType.APPLICATION_JSON_VALUE) > 0) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            try {
                if (ex instanceof ResultCodeException) {
                    ResultCodeException e = (ResultCodeException) ex;
                    response.setStatus(e.r.httpStatus);
                    response.getWriter().write(factory.getDecoder().toJson(ResBean.failed(e.r,e.getErrorText())));

                } else if (ex instanceof NullPointerException) {
                    response.setStatus(ResultCodeEnum.系统异常.httpStatus);
                    response.getWriter().write(factory.getDecoder().toJson(ResBean.failed(ResultCodeEnum.系统异常,"服务器内部错误")));

                } else if (ex instanceof RuntimeException) {
                    response.setStatus(ResultCodeEnum.系统异常.httpStatus);
                    response.getWriter().write(factory.getDecoder().toJson(ResBean.failed(ResultCodeEnum.系统异常,"服务器内部错误")));

                } else {
                    response.setStatus(ResultCodeEnum.系统异常.httpStatus);
                    response.getWriter().write(factory.getDecoder().toJson(ResBean.failed(ResultCodeEnum.系统异常,"服务器内部错误")));
                }
            } catch (IOException e) {
            }finally{
            }
        }

        return mv;
    }
}
