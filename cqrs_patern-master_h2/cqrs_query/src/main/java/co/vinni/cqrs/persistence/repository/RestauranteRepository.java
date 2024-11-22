package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Restaurante;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestauranteRepository extends MongoRepository<Restaurante, String> {
}