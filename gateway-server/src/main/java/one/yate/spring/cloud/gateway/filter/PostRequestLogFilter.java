package one.yate.spring.cloud.gateway.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

@Slf4j
public class PostRequestLogFilter extends ZuulFilter {
    /**
     * to classify a filter by type. Standard types in Zuul are "pre" for pre-routing filtering,
     * "route" for routing to an origin, "post" for post-routing filters, "error" for error handling.
     * We also support a "static" type for static responses see  StaticResponseFilter.
     * Any filterType made be created or added and run by calling FilterProcessor.runFilters(type)
     *
     * @return A String representing that type
     */
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext reqCtx = RequestContext.getCurrentContext();
        HttpServletRequest req = reqCtx.getRequest();

        InputStream stream = reqCtx.getResponseDataStream();
        String body = null;
        try {
            body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;
        try {
            map = mapper.readValue(body, new TypeReference<Map<String, String>>(){});
            map.put("code","500");

            reqCtx.setResponseBody("new body: "+map.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("code=500");

//        log.info("My PostRequestLogFilter print send {} request to {}", req.getMethod(), req.getRequestURL().toString());
//        return null;
    }
}
