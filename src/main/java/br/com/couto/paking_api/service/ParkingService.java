package br.com.couto.paking_api.service;

import br.com.couto.paking_api.Dtos.parkingRequestDto;
import br.com.couto.paking_api.Dtos.parkingResponseDto;
import br.com.couto.paking_api.Exception.UsuarioExcepiton;
import br.com.couto.paking_api.Exception.VeiculoExcepiton;
import br.com.couto.paking_api.domin.ParkingModel;
import br.com.couto.paking_api.repository.ParkingRepository;
import br.com.couto.paking_api.user.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;
    final UserRepository userRepository;

    public ParkingService(ParkingRepository parkingRepository, UserRepository userRepository) {
        this.parkingRepository = parkingRepository;
        this.userRepository = userRepository;
    }

    public parkingResponseDto cadastrarVeiculo(parkingRequestDto dto, UUID userId){
        var usuario = userRepository.findById(userId).orElseThrow(UsuarioExcepiton::new);
        ParkingModel carro = new ParkingModel(dto.placa(), dto.modelo(), dto.marca(), dto.cor());
        carro.setUser(usuario);
        return new parkingResponseDto(parkingRepository.save(carro));
    }

    public parkingResponseDto atualizarVeiculos(parkingRequestDto dto,Long id){
        var parking = parkingRepository.findById(id)
                .orElseThrow((VeiculoExcepiton::new));
        BeanUtils.copyProperties(dto,parking,"id","user");
        return new parkingResponseDto(parkingRepository.save(parking));


    }

    public Page<parkingResponseDto> obterVeiculos (Pageable pageable){
        return parkingRepository.findAll(pageable)
                .map(parkingResponseDto::new);

    }

    public Page<parkingResponseDto> buscarPorUsuario(UUID userId ,Pageable pageable ) {
      return parkingRepository.findByUserId(userId,pageable)
              .map(parkingResponseDto::new);
    }

    public void deletar(Long id){
        if (!parkingRepository.existsById(id)){
            throw new VeiculoExcepiton();
        }
        parkingRepository.deleteById(id);
    }
}
