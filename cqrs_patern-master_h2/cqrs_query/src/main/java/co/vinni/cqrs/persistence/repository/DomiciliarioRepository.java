package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Domiciliario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DomiciliarioRepository extends MongoRepository<Domiciliario, String> {

}