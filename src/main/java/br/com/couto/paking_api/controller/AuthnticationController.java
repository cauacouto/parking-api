package br.com.couto.paking_api.controller;

import br.com.couto.paking_api.Dtos.UsuarioRequestDto;
import br.com.couto.paking_api.Dtos.UsuarioResponseDto;
import br.com.couto.paking_api.infra.security.TokenService;
import br.com.couto.paking_api.user.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthnticationController {

    final UserRepository repository;
    final TokenService tokenService;
    final UserService userService;

    final AuthenticationManager authenticationManager;

    public AuthnticationController(UserRepository repository, TokenService tokenService, UserService userService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody authenticationDto dto){
        var usernamaPassword = new UsernamePasswordAuthenticationToken(dto.email(),dto.password());
        var auth = this.authenticationManager.authenticate(usernamaPassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> register(@RequestBody RegisterDTO dto){
      return ResponseEntity.status(HttpStatus.CREATED).body(userService.salvarusuario(dto));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<UsuarioResponseDto> atualizarUsuario(@RequestBody UsuarioRequestDto dto, UUID id){
        return ResponseEntity.ok().body(userService.atualizar(dto,id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<UsuarioResponseDto>> buscarPorPaginacao(@PageableDefault(size = 10,sort = "id") Pageable pageable){
        return ResponseEntity.ok().body(userService.buscarParcialmente(pageable));
    }

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseDto> perfil(){
        var usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.buscarPorId(usuario.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id){
        userService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
