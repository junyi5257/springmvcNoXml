package cn.junyi.springsecurity.model;

import java.io.Serializable;

/**
 * Created by GOUJY on 2018-01-04 9:50.
 *
 * Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 *
 * 服务端生成的Token一般为随机的非重复字符串，根据应用对安全性的不同要求，
 * 会将其添加时间戳（通过时间判断Token是否被盗用）或url签名（通过请求地址判断Token是否被盗用）后加密进行传输。
 *
 * 在本文中为了演示方便，仅是将User Id与Token以”_”进行拼接。
 *
 * @author goujy
 * @version 1.0
 */
public class TokenModel implements Serializable {
    private static final long serialVersionUID = 1278530148514466647L;
    //用户id
    private long userId;

    //随机生成的uuid
    private String token;

    public TokenModel(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
