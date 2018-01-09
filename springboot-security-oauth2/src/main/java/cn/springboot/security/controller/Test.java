package cn.springboot.security.controller;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by GOUJY on 2018-01-09 17:10.
 *
 * @author goujy
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {

        System.out.println("client_credentials:" + new String(Base64.encodeBase64("client_source:123456".getBytes())));
        System.out.println("password:" + new String(Base64.encodeBase64("client_order:123456".getBytes())));
    }
}
