package org.xaxox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MenuController {


    @ResponseBody
    @RequestMapping("/qwe")
    public String qwe(){

        return "[eq [eq[ eq";
    }



}
