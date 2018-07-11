package one.yate.spring.cloud.consumer.config;

import org.apache.commons.lang3.StringUtils;

public class ResultCodeException extends RuntimeException {
    public final ResultCodeEnum r;
    private String data;

    public ResultCodeException(ResultCodeEnum r) {
        this(r.name(), r);
    }

    public ResultCodeException(ResultCodeEnum r, String data) {
        this(r.name(), r);
        this.data = data;
    }

    public ResultCodeException(String msg, ResultCodeEnum r) {
        super(msg);
        this.r = r;
    }

    public ResultCodeException(Throwable ex, ResultCodeEnum r) {
        this(ex, r.defaultMsg, r);
    }

    public ResultCodeException(Throwable ex, String msg, ResultCodeEnum r) {
        super(msg, ex);
        this.r = r;
    }

    public String getErrorText() {
        return StringUtils.isBlank(data) ? r.name() : data;
    }


//    @Override
//    public Throwable fillInStackTrace() {
//        return this;
//    }
}
