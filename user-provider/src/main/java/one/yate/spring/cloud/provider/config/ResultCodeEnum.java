package one.yate.spring.cloud.provider.config;

public enum ResultCodeEnum {
    /*0-9999状态码务使用,系统级状态码*/
    成功(0, 200, "succeed"),
    未定义状态码(1, 503, "unknow error"),
    系统异常(2, 500, "service error"),
    参数异常(3, 400, "paramter error"),
    版本号无效(4, 403, "unknow version"),
    请求的资源不存在(5, 404, "not found"),
    跳转登陆(6,401,"go to login"),


    /*10000-49999内部系统对接返回状态码*/
    数据已存在(10000,200,"existed"),


    /*50000-99999为第三方系统返回状态码*/;
    public final int flag;
    public final int httpStatus;
    public final String defaultMsg;

    ResultCodeEnum(int f, int status, String msg) {
        this.flag = f;
        this.defaultMsg = msg;
        this.httpStatus = status;
    }

    public static ResultCodeEnum getByFlag(int v) {
        for (ResultCodeEnum x : ResultCodeEnum.values()) {
            if (x.flag == v) {
                return x;
            }
        }
        return null;
    }
}
