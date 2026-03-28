package br.com.couto.paking_api.repository;

import br.com.couto.paking_api.domin.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
