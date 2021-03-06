package cn.junyi.springsecurity.model;

/**
 * Created by GOUJY on 2018-01-04 11:02.
 *
 * @author goujy
 * @version 1.0
 */
public enum ResultStatus {
    SUCCESS(100, "success"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "username or password error"),
    USER_NOT_FOUND(-1002, "user not found"),
    USER_NOT_LOGIN(-1003, "user not login");

    private int code;
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
