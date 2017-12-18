package com.borroom.backend.exception;

import com.borroom.backend.exception.enums.ResultEnum;

public class RedefException extends RuntimeException {

    private Integer code;

    public RedefException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}