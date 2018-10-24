package feifei.exception;

/**
 * @ClassName PermissionException
 * @Description 当抛出的异常为我们自己定义的异常时，msg直接返回给用户，否则异常使用默认的异常
 * @Author : liutao
 * @Date : 2018/9/26 20:12
 * @Version : 1.0
 **/
public class PermissionException extends RuntimeException {

    //复写RuntimeException里面的方法
    public PermissionException() {
        super();
    }

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionException(Throwable cause) {
        super(cause);
    }

    protected PermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
