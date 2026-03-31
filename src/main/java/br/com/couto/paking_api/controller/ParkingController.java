package br.com.couto.paking_api.controller;

import br.com.couto.paking_api.Dtos.parkingRequestDto;
import br.com.couto.paking_api.Dtos.parkingResponseDto;
import br.com.couto.paking_api.service.ParkingService;
import br.com.couto.paking_api.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/parking")
public class ParkingController {


    private final ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<parkingResponseDto> cadastrarVeiculo(@RequestBody parkingRequestDto dto){
        var usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarVeiculo(dto, usuario.getId()));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<parkingResponseDto> atualizarCadastro(@RequestBody parkingRequestDto dto,Long id){
        return ResponseEntity.ok().body(service.atualizarVeiculos(dto,id));
    }

    @GetMapping
    public ResponseEntity<Page<parkingResponseDto>> listarmeusVeiculos(@PageableDefault(size = 10,sort = "id")Pageable pageable){
        var usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return ResponseEntity.ok(service.obterVeiculos(pageable));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<parkingResponseDto>> listarTodos(
            @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return ResponseEntity.ok(service.obterVeiculos(pageable));
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<Page<parkingResponseDto>> obterPorUsuario(@PathVariable UUID userId,Pageable pageable){
        return ResponseEntity.ok(service.buscarPorUsuario(userId,pageable));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
