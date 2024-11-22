package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
