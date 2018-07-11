package one.yate.spring.cloud.provider.config;

public interface JsonConvertFactory {
    interface ConvertEncoder {
        <T> T jsonTo(Class<T> clazz, String json);
    }

    interface ConvertDecoder {
        <T extends Object> String toJson(T t);
    }

    ConvertDecoder getDecoder();

    ConvertEncoder getEncoder();
}
