package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
}
