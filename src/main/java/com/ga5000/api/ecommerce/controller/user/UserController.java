package com.ga5000.api.ecommerce.controller.user;

import com.ga5000.api.ecommerce.dto.user.UserInfoResponse;
import com.ga5000.api.ecommerce.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

   @PostMapping
    public ResponseEntity<UserInfoResponse> getUserInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserInfo());
   }
}
