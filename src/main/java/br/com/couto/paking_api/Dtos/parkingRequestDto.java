package br.com.couto.paking_api.Dtos;

public record parkingRequestDto(String placa,
                                String modelo,
                                String marca,
                                String cor) {
}
