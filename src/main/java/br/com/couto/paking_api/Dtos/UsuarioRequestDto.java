package br.com.couto.paking_api.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDto {
    private String name;
    private String email;
    private String password;
    private String bloco;
    private String apartamento;
}