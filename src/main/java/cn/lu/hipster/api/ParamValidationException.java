package cn.lu.hipster.api;

/**
 * 参数异常
 *
 * @author lutiehua
 * @date 2017/9/28.
 */
public class ParamValidationException extends Exception {

    public ParamValidationException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private int errorCode = 0;

}
