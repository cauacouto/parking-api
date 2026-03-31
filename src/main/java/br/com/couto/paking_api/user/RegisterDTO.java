package br.com.couto.paking_api.user;

public record RegisterDTO(
        String email,
        String password,
        UserRole role,
        String name,
        String bloco,
        String apartamento,
        String placa,
        String modelo,
        String marca,
        String cor
) {}