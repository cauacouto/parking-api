package br.com.couto.paking_api.repository;

import br.com.couto.paking_api.domin.ParkingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParkingRepository extends JpaRepository<ParkingModel,Long> {


    Page<ParkingModel> findByUserId(UUID userId,Pageable pageable);
}
