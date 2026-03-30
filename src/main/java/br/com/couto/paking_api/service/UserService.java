package br.com.couto.paking_api.service;

import br.com.couto.paking_api.Dtos.UsuarioRequestDto;
import br.com.couto.paking_api.Dtos.UsuarioResponseDto;
import br.com.couto.paking_api.Exception.UsuarioExcepiton;
import br.com.couto.paking_api.domin.User;
import br.com.couto.paking_api.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UsuarioResponseDto salvarusuario(UsuarioRequestDto dto){
        System.out.println("Nome: " + dto.getName());
        var usuario = new User();
        BeanUtils.copyProperties(dto,usuario);
        System.out.println("Nome entity: " + usuario.getName());
        return new UsuarioResponseDto(repository.save(usuario));

    }

    public UsuarioResponseDto atualizar(UsuarioRequestDto dto, UUID id){
        var usuario = repository.findById(id).orElseThrow(UsuarioExcepiton::new);
        BeanUtils.copyProperties(dto,usuario,"id");
        return new UsuarioResponseDto(repository.save(usuario));

    }

    public Page<UsuarioResponseDto> buscarParcialmente(Pageable pageable){
        return repository.findAll(pageable)
                .map(UsuarioResponseDto::new);
    }

    public void deletarUsuario(UUID id){
         repository.deleteById(id);
    }
}
