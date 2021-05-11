package com.matrix.freshmarket.controller.Login;

import com.matrix.freshmarket.api.request.LoginRequest;
import com.matrix.freshmarket.api.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth/")
public class ApiAuthController {


    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest.getEmail());
        System.out.println(loginRequest.getPassword());
    return ResponseEntity.ok(new LoginResponse());
    }
}
