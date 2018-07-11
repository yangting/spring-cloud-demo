package one.yate.spring.cloud.provider.config;

public class JacksonConvertFactory implements JsonConvertFactory {
    public final ConvertDecoder defaultDecoder = new ConvertDecoder() {
        @Override
        public <T> String toJson(T t) {
            return JsonUtils.toJson(t);
        }
    };
    public final ConvertEncoder defaultEncoder = new ConvertEncoder() {
        @Override
        public <T> T jsonTo(Class<T> clazz, String json) {
            return JsonUtils.toObject(json, clazz);
        }
    };

    @Override
    public ConvertDecoder getDecoder() {
        return defaultDecoder;
    }

    @Override
    public ConvertEncoder getEncoder() {
        return defaultEncoder;
    }
}
