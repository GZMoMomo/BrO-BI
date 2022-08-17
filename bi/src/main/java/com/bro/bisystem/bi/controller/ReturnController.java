package com.bro.bisystem.bi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 专门用于返回html页面
  */
@RequestMapping("/")
@Controller
public class ReturnController {
    @RequestMapping("hello")
    public String hello(){
        return "hello";
    }
}
