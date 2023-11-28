//package com.cbo.riskmanagement.controller;
//
//import com.cbo.riskmanagement.model.AuthenticationRequest;
//import com.cbo.riskmanagement.model.AuthenticationResponse;
//import com.cbo.riskmanagement.model.RegisterRequest;
//import com.cbo.riskmanagement.model.UserResponseDetails;
//import com.cbo.riskmanagement.service.AuthenticationService;
//import com.cbo.riskmanagement.service.LogoutService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthenticationController {
//    @Autowired
//    AuthenticationService service;
//    @Autowired
//    LogoutService logoutService;
//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
//        return ResponseEntity.ok(service.register(request));
//    }
//    @PostMapping("/authenticate")
//    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
//                      System.out.println("Username: "+request.getUsername());
//                      System.out.println("Passworld: "+request.getPassword());
//        System.out.println( System.getProperty("user.home") + "\\Downloads\\uploads\\");
//            return ResponseEntity.ok(service.authenticate(request));
//    }
//
//    @PostMapping("/refresh-token")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        service.refreshToken(request, response);
//    }
//    @GetMapping("/logout")
//    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
//        logoutService.logout(request, response, authentication);
//    }
//}
