package org.surja.controller;


import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.surja.dto.UserDto;
import org.surja.service.UserService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user-service")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String > createUser(@RequestBody @Valid UserDto userDto) throws ExecutionException, InterruptedException {

        LOGGER.info("request for create user : {}",userDto.getEmail());
        return ResponseEntity.ok("user Id : " + userService.createUser(userDto));

    }


}
