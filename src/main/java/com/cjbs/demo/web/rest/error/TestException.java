package com.cjbs.demo.web.rest.error;


import com.cjbs.demo.service.utils.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shj
 */
@RestController
@RequestMapping("/error")
public class TestException {

    /**
     * testRuntimeException
     * @return ApiResult
     */
    @GetMapping("/testRuntimeException")
    public ApiResult testRuntimeException() {
        throw new RuntimeException();
    }

    /**
     * testArithmeticException
     * @return ApiResult
     */
    @GetMapping("/testArithmeticException")
    public ApiResult testArithmeticException() {
        throw new ArithmeticException();
    }

    /**
     * testIllegalArgumentException
     * @return ApiResult
     */
    @GetMapping("/testIllegalArgumentException")
    public ApiResult testException() {
        throw new IllegalArgumentException();
    }
}
