package com.balcao.uff.controller;

import com.balcao.uff.domain.User;
import com.balcao.uff.domain.dtos.AuthenticationDTO;
import com.balcao.uff.domain.dtos.LoginResponseDTO;
import com.balcao.uff.domain.dtos.RegisterDTO;
import com.balcao.uff.domain.dtos.UserDto;
import com.balcao.uff.security.TokenService;
import com.balcao.uff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated AuthenticationDTO data){
        UsernamePasswordAuthenticationToken emailPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(emailPassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token, new UserDto((User) auth.getPrincipal())));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Validated RegisterDTO data){
        if (userService.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(null, data.name(), encryptedPassword, data.email(), data.phone(), data.role(), null, true);

        userService.insert(newUser);

        return ResponseEntity.ok().body(new UserDto(newUser));
    }
}