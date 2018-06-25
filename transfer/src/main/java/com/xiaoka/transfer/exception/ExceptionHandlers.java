package com.xiaoka.transfer.exception;

import com.google.common.collect.Maps;
import com.kiaoka.common.exception.XiaokaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(XiaokaException.class)
    public ResponseEntity<Map> DataQualityException(XiaokaException e) {
        e.printStackTrace();
        Map<String,Object> map = Maps.newHashMap();
        map.put("httpCode",e.getHttpCode());
        map.put("errorCode",e.getErrorCode());
        map.put("errorMessage",e.getErrorMessage());
        map.put("data",e.getData());
        return new ResponseEntity<Map>(map, HttpStatus.valueOf(e.getHttpCode()));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map>  Exception(Exception e) {
        e.printStackTrace();
        Map<String,Object> map = Maps.newHashMap();
        map.put("httpCode",500);
        map.put("errorCode","InternalServerError");
        map.put("errorMessage",e.getMessage());
        return new ResponseEntity<Map>(map, HttpStatus.valueOf(500));
    }


}
