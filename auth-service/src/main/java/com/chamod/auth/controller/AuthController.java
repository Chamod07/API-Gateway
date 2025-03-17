package com.chamod.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication API", description = "Operations related to authentication")
public class AuthController {

    @GetMapping("/user-info")
    @Operation(summary = "Get user info", description = "Retrieves information about the current authenticated user")
    public Principal getUserInfo(Principal principal) {
        return principal;
    }
}
