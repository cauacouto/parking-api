package br.com.couto.paking_api.controller;

import br.com.couto.paking_api.Dtos.UsuarioRequestDto;
import br.com.couto.paking_api.Dtos.UsuarioResponseDto;
import br.com.couto.paking_api.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/V1/usuarios")
public class UsuarioController {

    private final UserService service;

    public UsuarioController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> salvarUsuario(@RequestBody UsuarioRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarusuario(dto));
    }

    @PutMapping
    public ResponseEntity<UsuarioResponseDto> atualizarUsuario(@RequestBody UsuarioRequestDto dto, UUID id){
       return ResponseEntity.ok().body(service.atualizar(dto,id));
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDto>> buscarPorPaginacao(@PageableDefault(size = 10,sort = "id") Pageable pageable){
        return ResponseEntity.ok().body(service.buscarParcialmente(pageable));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuario(UUID id){
        service.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
