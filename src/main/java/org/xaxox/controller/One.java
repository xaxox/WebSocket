package org.xaxox.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class One {

    @RequestMapping("/qwe")
    public String qwe(){
        return "OK";
    }

}
