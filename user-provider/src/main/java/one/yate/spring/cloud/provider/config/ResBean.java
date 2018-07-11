package one.yate.spring.cloud.provider.config;

public class ResBean {

    /**
     * 默认设置为成功状态，code=200
     * Response code;
     */
    private ResultCodeEnum code = ResultCodeEnum.成功;

    /**
     * 错误消息Set到此属性
     * Response message;
     */
    private String msg = "";

    /**
     * 返回为单个对象时，set到此属性
     * Response Object;
     */
    private Object data = null;

    private ResBean(ResultCodeEnum code) {
        this.code = code;
        this.msg = code.defaultMsg;
    }

    private ResBean(ResultCodeEnum code, Object data) {
        super();
        this.code = code;
        this.msg = code.defaultMsg;
        this.data = data;
    }

    private ResBean(ResultCodeEnum code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public <T> Object getObj(Class<T> T) {
        if (data instanceof String) {
            String json = data.toString();
            return JsonUtils.toObject(json, T);
        }
        return null;
    }

    public int getCode() {
        return code.flag;
    }

    public void setCode(ResultCodeEnum code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public void setData(Object data) {
        this.data = data;
    }


    public Object getData() {
        return data;
    }

    public static final ResBean builder(ResultCodeEnum err, String msg, Object data) {
        return new ResBean(err, msg, data);
    }

    public static final ResBean success() {
        return new ResBean(ResultCodeEnum.成功);
    }

    public static final ResBean success(Object data) {
        return new ResBean(ResultCodeEnum.成功, data);
    }

    public static final ResBean success(ResultCodeEnum r, Object data) {
        return new ResBean(r, data);
    }

    public static final ResBean failed(ResultCodeEnum err) {
        return new ResBean(err, null);
    }

    public static final ResBean failed(ResultCodeEnum err, String msg) {
        return new ResBean(err, msg);
    }
}
