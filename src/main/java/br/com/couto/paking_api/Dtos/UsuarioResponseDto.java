package br.com.couto.paking_api.Dtos;

import br.com.couto.paking_api.domin.User;

import java.util.UUID;

public record UsuarioResponseDto(UUID id,
         String name,
         String email,
        String password,
         String bloco,
         String apartamento) {

    public UsuarioResponseDto(User user){
        this(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getBloco(),
                user.getApartamento()
        );
    }
}
