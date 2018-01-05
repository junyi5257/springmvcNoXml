package cn.junyi.springsecurity.model;

/**
 * Created by GOUJY on 2018-01-04 11:01.
 * ResultMsg Defined
 *
 * @author goujy
 * @version 1.0
 */
public class ResultModel {
    /**
     * return result number
     */
    private int code;
    /**
     * return result massage
     */
    private String message;
    /**
     * return result content
     */
    private Object content;

    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = "";
    }

    public ResultModel(ResultStatus status, Object content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = content;
    }

    public static ResultModel ok(Object content) {
        return new ResultModel(ResultStatus.SUCCESS, content);
    }

    public static ResultModel ok() {
        return ok("");
    }

    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultModel(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
