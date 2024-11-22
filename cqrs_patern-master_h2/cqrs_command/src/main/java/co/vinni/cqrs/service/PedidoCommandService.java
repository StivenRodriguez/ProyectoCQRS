package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.*;
import co.vinni.cqrs.persistence.entity.Domiciliario;
import co.vinni.cqrs.persistence.entity.Pedido;
import co.vinni.cqrs.persistence.entity.Restaurante;
import co.vinni.cqrs.persistence.entity.Usuario;
import co.vinni.cqrs.persistence.repository.DomiciliarioRepository;
import co.vinni.cqrs.persistence.repository.PedidoRepository;
import co.vinni.cqrs.persistence.repository.RestauranteRepository;
import co.vinni.cqrs.persistence.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PedidoCommandService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DomiciliarioRepository domiciliarioRepository;
    private final RestauranteRepository restauranteRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // Paso 1: Crear un nuevo pedido y emitir el evento PedidoCreado
    @Transactional
    public Pedido createPedido(PedidoDatos pedidoDatos) {

        Usuario usuario = usuarioRepository.findById(pedidoDatos.getUsuarioId()).get();
        Restaurante restaurante = restauranteRepository.findById(pedidoDatos.getRestauranteId()).get();

        Pedido pedido = new Pedido();
        pedido.setDetallePedido(pedidoDatos.getDetallePedido());
        pedido.setUsuario(usuario);
        pedido.setRestaurante(restaurante);
        pedido.setFechaCreacion(LocalDateTime.now());
        pedido.setFechaActualizacion(LocalDateTime.now());

        Pedido nuevoPedido = pedidoRepository.save(pedido);
        PedidoCreadoEvent event = new PedidoCreadoEvent("PedidoCreado", nuevoPedido);
        kafkaTemplate.send("pedido-event-topic", event);
        return nuevoPedido;
    }

    // Paso 2: El restaurante recibe el pedido y actualiza el estado, emitiendo EstadoRestauranteActualizado
    @Transactional
    public Pedido actualizarEstadoRestaurante(Long pedidoId, Long restauranteId, String nuevoEstado) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoId);
        if (pedidoOpt.isPresent()) {
            Restaurante restaurante = restauranteRepository.findById(restauranteId).get();
            Pedido pedido = pedidoOpt.get();

            pedido.setRestaurante(restaurante);
            pedido.setEstadoRestaurante(nuevoEstado);
            Pedido pedidoActualizado = pedidoRepository.save(pedido);
            EstadoRestauranteActualizadoEvent event = new EstadoRestauranteActualizadoEvent("EstadoRestauranteActualizado", pedidoActualizado);
            kafkaTemplate.send("estado-pedido-restaurante-event-topic", event);
            return pedidoActualizado;
        } else {
            throw new IllegalArgumentException("Pedido no encontrado");
        }
    }

    // Paso 3: El domiciliario recibe el pedido y actualiza el estado, emitiendo EstadoDomiciliarioActualizado
    @Transactional
    public Pedido actualizarEstadoDomiciliario(Long pedidoId, Long domiciliarioId, String nuevoEstado) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(pedidoId);
        if (pedidoOpt.isPresent()) {
            Domiciliario domiciliario = domiciliarioRepository.findById(domiciliarioId).get();
            Pedido pedido = pedidoOpt.get();

            pedido.setDomiciliario(domiciliario);
            pedido.setEstadoDomiciliario(nuevoEstado);
            Pedido pedidoActualizado = pedidoRepository.save(pedido);
            EstadoDomiciliarioActualizadoEvent event = new EstadoDomiciliarioActualizadoEvent("EstadoDomiciliarioActualizado", pedidoActualizado);
            kafkaTemplate.send("estado-pedido-domiciliario-event-topic", event);
            return pedidoActualizado;
        } else {
            throw new IllegalArgumentException("Pedido no encontrado");
        }
    }
}

