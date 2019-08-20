package com.cjbs.demo.web.rest;

import com.cjbs.demo.domain.User;
import com.cjbs.demo.service.UserService;
import com.cjbs.demo.web.dto.UserDTO;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author shj
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据姓名查询年龄
     *
     * @param name name
     * @return User
     */
    @GetMapping("/Name")
    @Timed
    public ResponseEntity<User> getAgeByName(@RequestParam String name) {
        logger.debug("REST to request get age by name");

        User user = userService.getAgeByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * 新增User
     *
     * @param userDTO userDTO
     * @return UserDTO
     */
    @PostMapping("/create")
    @Timed
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        UserDTO createUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }

}
