package org.xaxox.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class One {


    @Autowired
    private Gson gson;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return gson.toJson(Collections.singletonMap("key", "value"));
    }

}
