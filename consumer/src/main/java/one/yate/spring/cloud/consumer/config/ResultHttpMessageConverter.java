package one.yate.spring.cloud.consumer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ResultHttpMessageConverter extends AbstractJackson2HttpMessageConverter {
    public ResultHttpMessageConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        return mediaType.includes(MediaType.APPLICATION_JSON)
                || mediaType.includes(MediaType.APPLICATION_JSON_UTF8);
    }

    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.singletonList(MediaType.APPLICATION_JSON);
    }

    @Override
    public Object read(Type type, @Nullable Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputMessage.getBody(), writer);
            String theString = writer.toString();

            JavaType javaType = getJavaType(type, contextClass);
            try {
                ResBean r = this.objectMapper.readValue(theString, ResBean.class);
                if(javaType.hasRawClass(ResBean.class)){
                    return r;
                }
                return this.objectMapper.readValue(objectMapper.writeValueAsString(r.getData()), javaType);
            } catch (Exception e) {
            }
            return this.objectMapper.readValue(theString, javaType);
        } catch (InvalidDefinitionException ex) {
            throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
        } catch (JsonProcessingException ex) {
            throw new HttpMessageNotReadableException("JSON parse error: " + ex.getOriginalMessage(), ex);
        }
    }
}

