package com.cjbs.demo.web.rest;


import com.cjbs.demo.service.oauth.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shj
 */
@RestController
@RequestMapping("/api")
public class JWTController {

    private final Logger logger = LoggerFactory.getLogger(JWTController.class);

    private JWTService jwtService;

    public JWTController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/getToken")
    public ResponseEntity<String> getToken() {
        logger.debug("JWTController getToken");
        String token = jwtService.getToken();
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
