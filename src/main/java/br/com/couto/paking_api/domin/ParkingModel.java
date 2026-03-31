package br.com.couto.paking_api.domin;

import br.com.couto.paking_api.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_PAKING")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String placa;
    private String modelo;
    private String marca;
    private String cor;

    public ParkingModel(String placa, String modelo, String marca, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.cor = cor;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
