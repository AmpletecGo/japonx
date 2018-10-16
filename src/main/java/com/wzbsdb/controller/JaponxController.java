package com.wzbsdb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/9/20
 * @since 1.0.0
 */
@Controller
@RequestMapping("/japonx")
public class JaponxController {

    @GetMapping("/list")
    public String list(){
        return "";
    }

}
