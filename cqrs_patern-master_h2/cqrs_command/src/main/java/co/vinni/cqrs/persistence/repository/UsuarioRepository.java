package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
