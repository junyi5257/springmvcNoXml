package cn.junyi.springsecurity.config;

/**
 * Created by GOUJY on 2018-01-04 10:18.
 *
 * @author goujy
 * @version 1.0
 */
public class Constants {
    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";
}
