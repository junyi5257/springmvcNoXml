package cn.springboot.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by GOUJY on 2018-01-09 14:18.
 * 既然说到保护应用，那必须得先有一些资源
 * 这个Controller下的接口，设置为需要保护的资源(请求)
 *
 * @author goujy
 * @version 1.0
 */
@RestController
public class ApiController {

    @GetMapping("/source/{id}")
    public String getSource(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "source:" + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order:" + id;
    }

}
