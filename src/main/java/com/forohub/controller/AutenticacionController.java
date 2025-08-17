package com.forohub.controller;

import com.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacionController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<DatosJWTToken> login(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.password());
        Authentication usuarioAutenticado = authenticationManager.authenticate(authToken);

        String jwt = tokenService.generarToken(usuarioAutenticado.getName());
        return ResponseEntity.ok(new DatosJWTToken(jwt));
    }

    public record DatosAutenticacionUsuario(@NotBlank String login, @NotBlank String password) {}
    public record DatosJWTToken(String token) {}
}
