package cn.junyi.springsecurity.controller;

import cn.junyi.springsecurity.model.ResultModel;
import cn.junyi.springsecurity.model.ResultStatus;
import cn.junyi.springsecurity.model.TokenModel;
import cn.junyi.springsecurity.model.User;
import cn.junyi.springsecurity.service.TokenManager;
import cn.junyi.springsecurity.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by GOUJY on 2018-01-04 10:45.
 * Token对象的获取Controller
 *
 * @author goujy
 * @version 1.0
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    TokenManager tokenManager;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResultModel> getToken(@RequestParam String username, @RequestParam String password) {
        // Assert.notNull(username, "username can not be empty");
        // Assert.notNull(password, "password can not be empty");
        User user = userRepository.findByUsername(username);
        ////未注册 OR 密码错误
        if (user == null || !user.getPassword().equals(password)) {
            //提示用户名或密码错误
            return new ResponseEntity<ResultModel>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        //生成一个token，保存用户登录状态
        TokenModel model = tokenManager.createToken(user.getId());
        return new ResponseEntity<ResultModel>(ResultModel.ok(model), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<ResultModel> deleteToken(@RequestBody User user) {
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity<ResultModel>(ResultModel.ok(), HttpStatus.OK);
    }

    @GetMapping("/")
    public String apiDoc() {
        return "API-DOC";
    }
}
