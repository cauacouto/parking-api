package br.com.couto.paking_api.Dtos;

import java.util.UUID;

public record parkingRequestDto(String placa,
                                String modelo,
                                String marca,
                                String cor,
                                UUID userId
                                ) {
}
