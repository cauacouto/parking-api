package br.com.couto.paking_api.Dtos;

import br.com.couto.paking_api.domin.ParkingModel;

import java.util.UUID;

public record parkingResponseDto(Long id,
                                 String placa,
                                 String modelo,
                                 String marca,
                                 String cor,
                                 UUID userId,
                                 String nomeUsuario

                                 ) {


public parkingResponseDto(ParkingModel parkingModel) {

    this(parkingModel.getId(),
            parkingModel.getPlaca(),
            parkingModel.getModelo(),
            parkingModel.getMarca(),
            parkingModel.getCor(),
            parkingModel.getUser().getId(),
            parkingModel.getUser().getName()

    );
}
}
