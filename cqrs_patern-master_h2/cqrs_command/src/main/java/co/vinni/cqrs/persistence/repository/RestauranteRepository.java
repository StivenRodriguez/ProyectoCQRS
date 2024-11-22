package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
