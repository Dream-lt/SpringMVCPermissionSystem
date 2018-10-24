package feifei.exception;

/**
 * @ClassName ParamException
 * @Description 定义前台往后台传入参数的异常
 * @Author : liutao
 * @Date : 2018/9/26 21:35
 * @Version : 1.0
 **/
public class ParamException extends RuntimeException{
    public ParamException() {
        super();
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

    protected ParamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
