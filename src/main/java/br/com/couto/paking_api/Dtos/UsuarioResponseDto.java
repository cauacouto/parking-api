package br.com.couto.paking_api.Dtos;

import br.com.couto.paking_api.domin.ParkingModel;
import br.com.couto.paking_api.user.User;
import br.com.couto.paking_api.user.UserRole;

import java.util.List;
import java.util.UUID;

public record UsuarioResponseDto(UUID id,
                                 String name,
                                 String email,
                                 String bloco,
                                 String apartamento,
                                 UserRole role,
                                 List<ParkingModel> veiculos) {

    public UsuarioResponseDto(User user){
        this(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getBloco(),
                user.getApartamento(),
                user.getRole(),
                user.getVeiculos()
        );
    }
}
