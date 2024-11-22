package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}