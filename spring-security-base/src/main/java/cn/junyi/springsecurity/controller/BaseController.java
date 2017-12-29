package cn.junyi.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * Created by goujy on 2017/12/27.
 *
 * @author goujy
 */
@Controller
@RequestMapping("/base")
public class BaseController {


    @RequestMapping
    @ResponseBody
    public String base() {
        return "SUCCESS";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @RequestMapping("/home")
    public String home(Map<String, Object> model) {
        model.put("message", "Hello World");
        model.put("title", "Hello 中国Home");
        model.put("date", new Date());
        return "home";
    }
}
