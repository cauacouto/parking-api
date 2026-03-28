package br.com.couto.paking_api.domin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB-PAKING")
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
