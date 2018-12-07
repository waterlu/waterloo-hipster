package ${packageName};

import cn.lu.web.mvc.ExceptionInfo;

/**
 * ${classRemark}错误码定义
 *
 * @author ${author}
 * @date ${date}
 */
public enum ${className} implements ExceptionInfo {
    // common exception
    COMMON_EXCEPTION(${baseErrorCode?c}, "common exception");

    /**
     * 错误编码
     *
     */
    private int code;

    /**
     * 错误信息
     *
     */
    private String message;

    /**
     * 构造函数
     *
     */
    ${className}(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}