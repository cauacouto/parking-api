package br.com.couto.paking_api.repository;

import br.com.couto.paking_api.domin.ParkingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<ParkingModel,Long> {
}
