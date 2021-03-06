package one.yate.spring.cloud.consumer.config;

public class ResBean {

    private ResultCodeEnum code = ResultCodeEnum.成功;

    private String msg = "";

    private Object data = null;
    public ResBean(){
    }

    public ResBean(ResultCodeEnum code) {
        this.code = code;
        this.msg = code.defaultMsg;
    }

    public ResBean(ResultCodeEnum code, Object data) {
        super();
        this.code = code;
        this.msg = code.defaultMsg;
        this.data = data;
    }

    public ResBean(ResultCodeEnum code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
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
