package com.borroom.backend.exception.handle;

import com.borroom.backend.domain.Result;
import com.borroom.backend.exception.RedefException;
import com.borroom.backend.utils.ResultUtil;
import org.slf4j.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if(e instanceof RedefException) {
            RedefException redefException = (RedefException) e;
            return ResultUtil.error(redefException.getCode(), redefException.getMessage());
        } else {
            logger.error("[系统错误]{}", e);
            return ResultUtil.error(-1, "未知错误");
        }
    }
}
