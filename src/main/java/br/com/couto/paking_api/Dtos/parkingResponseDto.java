package br.com.couto.paking_api.Dtos;

public record parkingResponseDto(Long id,
        String placa,
         String modelo,
         String marca,
         String cor) {
}
