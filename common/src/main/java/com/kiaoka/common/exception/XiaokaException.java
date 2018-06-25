package com.kiaoka.common.exception;

/**
 *
 * 统一处理错误异常消息格式
 */
public class XiaokaException extends RuntimeException {
    private int httpCode;//http错误码
    private String errorCode;//业务错误码
    private String errorMessage;//错误详情
    private Object data;//其它数据


    public XiaokaException(int httpCode, String errorCode, String errorMessage) {
        this.httpCode = httpCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return " httpCode:"+ httpCode +"\n errorCode:"+errorCode+"\n errorMessage:"+ errorMessage;
    }
}
