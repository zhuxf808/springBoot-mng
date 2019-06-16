package com.zhuxf.common;

import com.zhuxf.common.enums.ResultEnum;

public class ResultData {

    private Integer code;

    private String message;

    private Object data;


    public static ResultData  isOk(){
        return new ResultData(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),null);
    }

    public static ResultData isOk(Object data){
        return new ResultData(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),data);
    }

    public static ResultData isFail(){
        return new ResultData(ResultEnum.FAIL.getCode(),ResultEnum.FAIL.getMessage());
    }

    public static ResultData isFail(ResultEnum resultEnum){
        return new ResultData(resultEnum.getCode(),resultEnum.getMessage());
    }

    public static ResultData build(Integer code,String message,Object data){
        return new ResultData(code, message, data);
    }

    public ResultData(Integer code) {
        this.code = code;
    }

    public ResultData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultData(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
