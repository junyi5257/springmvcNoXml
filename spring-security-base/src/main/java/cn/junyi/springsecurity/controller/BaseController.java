package cn.junyi.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
