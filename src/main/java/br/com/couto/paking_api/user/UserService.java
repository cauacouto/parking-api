package br.com.couto.paking_api.user;

import br.com.couto.paking_api.Dtos.UsuarioRequestDto;
import br.com.couto.paking_api.Dtos.UsuarioResponseDto;
import br.com.couto.paking_api.Exception.UsuarioExcepiton;
import br.com.couto.paking_api.domin.ParkingModel;
import br.com.couto.paking_api.repository.ParkingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

private final UserRepository UserRepository;
private final ParkingRepository parkingRepository;

    public UserService(UserRepository UserRepository, ParkingRepository parkingRepository) {
        this.UserRepository = UserRepository;
        this.parkingRepository = parkingRepository;
    }

    public UsuarioResponseDto salvarusuario(RegisterDTO dto){
        if (this.UserRepository.findByEmail(dto.email()) != null)
            throw new UsuarioExcepiton();
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.email(),encryptedPassword,dto.role(),dto.name(),dto.bloco(),dto.apartamento());
      UserRepository.save(newUser);

        ParkingModel carro = new ParkingModel(dto.placa(), dto.marca(), dto.modelo(),dto.cor());
        carro.setUser(newUser);
        parkingRepository.save(carro);

        return new UsuarioResponseDto(UserRepository.save(newUser));

    }

    public UsuarioResponseDto atualizar(UsuarioRequestDto dto, UUID id){
        var usuario = UserRepository.findById(id).orElseThrow(UsuarioExcepiton::new);
        BeanUtils.copyProperties(dto,usuario,"id");
        return new UsuarioResponseDto(UserRepository.save(usuario));

    }

    public Page<UsuarioResponseDto> buscarParcialmente(Pageable pageable){
        return UserRepository.findAll(pageable)
                .map(UsuarioResponseDto::new);
    }

    public UsuarioResponseDto buscarPorId(UUID id){
        return new UsuarioResponseDto(UserRepository.findById(id).orElseThrow(UsuarioExcepiton::new));
    }

    public void deletarUsuario(UUID id){
         UserRepository.deleteById(id);
    }
}
