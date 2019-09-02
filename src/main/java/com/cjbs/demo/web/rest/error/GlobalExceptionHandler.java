package com.cjbs.demo.web.rest.error;

import com.cjbs.demo.service.utils.ApiResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author shj
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 运行时异常
     * @param ex ex
     * @return String
     */
    @ExceptionHandler(RuntimeException.class)
    public ApiResultUtils runtimeExceptionHandler(RuntimeException ex) {
        ex.printStackTrace();
        return new ApiResultUtils(12, ex.getMessage(), ex.getStackTrace());

    }

    /**
     * 除数不能为0
     * @param ex ex
     * @return String
     */
    @ExceptionHandler(ArithmeticException.class)
    public ApiResultUtils arithmeticException(ArithmeticException ex) {
        ex.printStackTrace();
        return new ApiResultUtils(13, ex.getMessage(), ex.getStackTrace());
    }

    /**
     * 其他错误
     * @param ex ex
     * @return String
     */
    @ExceptionHandler(Exception.class)
    public ApiResultUtils exception(Exception ex) {
        ex.printStackTrace();
        return new ApiResultUtils(14, ex.getMessage(), ex.getStackTrace());
    }

}
