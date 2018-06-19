package com.xiaoka.lbs.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/t")
@Api(value = "测试")
public class TestController {


    @ApiOperation(value = "向客户端app发送消息")
    @ApiResponses({@ApiResponse(code=500,message="INTERNAL_SERVER_ERROR")})
    @RequestMapping(value = "/g",method = RequestMethod.GET)
    public String gg(@ApiParam(value = "消息",required = true) @RequestParam(value = "t",required = true) String t) {
        System.out.println("t=="+t);
        return "OK";
    }
}
