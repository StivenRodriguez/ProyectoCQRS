package co.vinni.cqrs.service;


import co.vinni.cqrs.dto.EstadoDomiciliarioActualizadoEvent;
import co.vinni.cqrs.dto.EstadoRestauranteActualizadoEvent;
import co.vinni.cqrs.dto.PedidoCreadoEvent;
import co.vinni.cqrs.persistence.entity.Pedido;
import co.vinni.cqrs.persistence.repository.PedidoRepository;
import co.vinni.cqrs.persistence.repository.RestauranteRepository;
import co.vinni.cqrs.persistence.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoQueryService {
    PedidoRepository pedidoRepository;
    UsuarioRepository usuarioRepository;
    RestauranteRepository restauranteRepository;

    public List<Pedido> getAll() {
        return this.pedidoRepository.findAll();
    }

    public Pedido getById(String id) {
        return this.pedidoRepository.findById(id).get();
    }

    @KafkaListener(topics = "pedido-event-topic", groupId = "pedido-event-group")
    public void processPedidoEvents(PedidoCreadoEvent pedidoEvent) {
        Pedido pedido = pedidoEvent.getPedido();

        if (pedidoEvent.getEventType().equals("PedidoCreado")) {
            pedidoRepository.save(pedido);
        }
    }

    @KafkaListener(topics = "estado-pedido-restaurante-event-topic", groupId = "pedido-event-group")
    public void processEstadoPedidoRestauranteActualizadoEvents(EstadoRestauranteActualizadoEvent estadoRestauranteActualizadoEvent) {
        Pedido pedido = estadoRestauranteActualizadoEvent.getPedido();

        if (estadoRestauranteActualizadoEvent.getEventType().equals("EstadoRestauranteActualizado")) {
            Pedido existingPedido = pedidoRepository.findById(pedido.getId()).get();
            existingPedido.setRestaurante(pedido.getRestaurante());
            existingPedido.setEstadoRestaurante(pedido.getEstadoRestaurante());
            existingPedido.setFechaActualizacion(LocalDateTime.now());

            pedidoRepository.save(existingPedido);
        }
    }

    @KafkaListener(topics = "estado-pedido-domiciliario-event-topic", groupId = "pedido-event-group")
    public void processEstadoPedidoDomiciliarioActualizadoEventEvents(EstadoDomiciliarioActualizadoEvent estadoDomiciliarioActualizadoEvent) {
        Pedido pedido = estadoDomiciliarioActualizadoEvent.getPedido();

        if (estadoDomiciliarioActualizadoEvent.getEventType().equals("EstadoDomiciliarioActualizado")) {
            Pedido existingPedido = pedidoRepository.findById(pedido.getId()).get();
            existingPedido.setDomiciliario(pedido.getDomiciliario());
            existingPedido.setEstadoDomiciliario(pedido.getEstadoDomiciliario());
            existingPedido.setFechaActualizacion(LocalDateTime.now());

            pedidoRepository.save(existingPedido);
        }
    }


}
