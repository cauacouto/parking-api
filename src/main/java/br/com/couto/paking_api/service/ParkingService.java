package br.com.couto.paking_api.service;

import br.com.couto.paking_api.Dtos.parkingRequestDto;
import br.com.couto.paking_api.Dtos.parkingResponseDto;
import br.com.couto.paking_api.Exception.VeiculoExcepiton;
import br.com.couto.paking_api.domin.ParkingModel;
import br.com.couto.paking_api.repository.ParkingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

    final ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }

    public parkingResponseDto cadastrartVeiculos(parkingRequestDto dto){

        var paking = new ParkingModel();
        BeanUtils.copyProperties(dto,paking);
       var salvar = repository.save(paking);
       return new parkingResponseDto(salvar.getId(),
               salvar.getPlaca(), salvar.getModelo(),salvar.getMarca(),salvar.getCor());
    }

    public parkingResponseDto atualizarVeiculos(parkingRequestDto dto,Long id){
        var parking = repository.findById(id)
                .orElseThrow((VeiculoExcepiton::new));
        BeanUtils.copyProperties(dto,parking,"id");
        var salvar = repository.save(parking);
        return new parkingResponseDto(salvar.getId(),
                salvar.getPlaca(), salvar.getModelo(),salvar.getMarca(),salvar.getCor());


    }
}
