package one.yate.spring.cloud.consumer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ResultHttpMessageConverter extends AbstractJackson2HttpMessageConverter {
    protected ResultHttpMessageConverter(ObjectMapper objectMapper) {
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
            if (inputMessage instanceof MappingJacksonInputMessage) {
                Class<?> deserializationView = ((MappingJacksonInputMessage) inputMessage).getDeserializationView();
                if (deserializationView != null) {
                    ResBean r = this.objectMapper.readerWithView(deserializationView).forType(ResBean.class).
                            readValue(inputMessage.getBody());
                    if (r.getCode() == null) {
                        // 不可转换
                        throw new HttpMessageConversionException("转换ResBean出错");
                    }
                    JavaType javaType = getJavaType(type, contextClass);
                    return this.objectMapper.readValue(objectMapper.writeValueAsString(r.getData()), javaType);
                }
            }
            ResBean r = this.objectMapper.readValue(inputMessage.getBody(), ResBean.class);
            if (r.getCode() == null) {
                // 不可转换
                throw new HttpMessageConversionException("转换ResBean出错");
            }
            JavaType javaType = getJavaType(type, contextClass);
            return this.objectMapper.readValue(objectMapper.writeValueAsString(r.getData()), javaType);
        } catch (InvalidDefinitionException ex) {
            throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
        } catch (JsonProcessingException ex) {
            throw new HttpMessageNotReadableException("JSON parse error: " + ex.getOriginalMessage(), ex);
        }
    }
}

