package com.xiaoka.lbs.controller;

import com.xiaoka.lbs.service.TestService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/t")
@Api(value = "测试")
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "向客户端app发送消息")
    @ApiResponses({@ApiResponse(code=500,message="INTERNAL_SERVER_ERROR")})
    @RequestMapping(value = "/g",method = RequestMethod.GET)
    public String gg(@ApiParam(value = "消息",required = true) @RequestParam(value = "t",required = true) String t) {
        System.out.println("t=="+t);
        return "OK";
    }

    @ApiOperation(value = "redis测试")
    @ApiResponses({@ApiResponse(code=500,message="INTERNAL_SERVER_ERROR")})
    @RequestMapping(value = "/rt",method = RequestMethod.GET)
    public String redisTest(@ApiParam(value = "key",required = true) @RequestParam(value = "key",required = true) String key,
                            @ApiParam(value = "value",required = true) @RequestParam(value = "value",required = true) String value) {
        System.out.println("key=="+key+"  value="+value);
        System.out.println("testService="+testService);
        testService.test(key,value);
        return "OK";
    }
}
