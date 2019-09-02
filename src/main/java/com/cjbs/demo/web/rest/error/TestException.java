package com.cjbs.demo.web.rest.error;


import com.cjbs.demo.service.utils.ApiResultUtils;
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
     * @return ApiResultUtils
     */
    @GetMapping("/testRuntimeException")
    public ApiResultUtils testRuntimeException() {
        throw new RuntimeException();
    }

    /**
     * testArithmeticException
     * @return ApiResultUtils
     */
    @GetMapping("/testArithmeticException")
    public ApiResultUtils testArithmeticException() {
        throw new ArithmeticException();
    }

    /**
     * testIllegalArgumentException
     * @return ApiResultUtils
     */
    @GetMapping("/testIllegalArgumentException")
    public ApiResultUtils testException() {
        throw new IllegalArgumentException();
    }
}
