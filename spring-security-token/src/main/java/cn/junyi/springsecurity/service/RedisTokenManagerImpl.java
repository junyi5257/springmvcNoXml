package cn.junyi.springsecurity.service;

import cn.junyi.springsecurity.config.Constants;
import cn.junyi.springsecurity.model.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by GOUJY on 2018-01-04 10:00.
 *
 * @author goujy
 * @version 1.0
 */
@Component
public class RedisTokenManagerImpl implements TokenManager {

   // @Autowired
    private RedisTemplate redis;

    @Autowired
    public void setRedis(RedisTemplate redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }


    @Override
    public TokenModel createToken(long userId) {
        //使用UUID生成原始Token
        String token = UUID.randomUUID().toString().replace("_", "");
        TokenModel tokenModel = new TokenModel(userId, token);

        //存储到redis并设置过期时间
        redis.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);

        return null;
    }

    @Override
    public boolean checkToken(TokenModel tokenModel) {
        if (tokenModel == null) {
            return false;
        }
        String token = (String) redis.boundValueOps(tokenModel.getUserId()).get();
        if (token == null || !token.equals(tokenModel.getToken())) {
            return false;
        }
        //验证成功,说明用户进行了一次有效操作,延长token的过期时间
        redis.boundValueOps(tokenModel.getUserId()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new TokenModel(userId, token);
    }

    @Override
    public void deleteToken(long userId) {
       redis.delete(userId);
    }
}
