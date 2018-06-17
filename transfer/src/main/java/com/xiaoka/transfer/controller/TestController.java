package com.xiaoka.transfer.controller;

import com.xiaoka.transfer.model.User;
import com.xiaoka.transfer.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/tt")
    public User getReports() {
        System.out.println("11111111111111111");
        return testService.findUser(1);
    }

}
