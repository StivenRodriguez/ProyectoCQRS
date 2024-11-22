package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Domiciliario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomiciliarioRepository extends JpaRepository<Domiciliario, Long> {
}
