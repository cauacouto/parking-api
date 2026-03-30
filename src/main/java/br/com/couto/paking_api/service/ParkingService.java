package br.com.couto.paking_api.service;

import br.com.couto.paking_api.Dtos.parkingRequestDto;
import br.com.couto.paking_api.Dtos.parkingResponseDto;
import br.com.couto.paking_api.Exception.UsuarioExcepiton;
import br.com.couto.paking_api.Exception.VeiculoExcepiton;
import br.com.couto.paking_api.domin.ParkingModel;
import br.com.couto.paking_api.repository.ParkingRepository;
import br.com.couto.paking_api.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParkingService {

    final ParkingRepository repository;
    final UserRepository userRepository;

    public ParkingService(ParkingRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public parkingResponseDto cadastrartVeiculos(parkingRequestDto dto){
         var parking = new ParkingModel();
        BeanUtils.copyProperties(dto,parking);
        var usuario = userRepository.findById(dto.userId()).orElseThrow(UsuarioExcepiton::new);
        parking.setUser(usuario);
        return new parkingResponseDto(repository.save(parking));
    }

    public parkingResponseDto atualizarVeiculos(parkingRequestDto dto,Long id){
        var parking = repository.findById(id)
                .orElseThrow((VeiculoExcepiton::new));
        BeanUtils.copyProperties(dto,parking,"id");
        var usuario = userRepository.findById(dto.userId()).orElseThrow(UsuarioExcepiton::new);
        parking.setUser(usuario);
        return new parkingResponseDto(repository.save(parking));


    }

    public Page<parkingResponseDto> obterVeiculos (Pageable pageable){
        return repository.findAll(pageable)
                .map(parkingResponseDto::new);

    }

    public Page<parkingResponseDto> buscarPorUsuario(UUID userId ,Pageable pageable ) {
      return repository.findByUserId(userId,pageable)
              .map(parkingResponseDto::new);
    }

    public void deletar(Long id){
        if (!repository.existsById(id)){
            throw new VeiculoExcepiton();
        }
        repository.deleteById(id);
    }
}
