package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.RestauranteEvent;
import co.vinni.cqrs.persistence.entity.Restaurante;
import co.vinni.cqrs.persistence.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestauranteCommandService {

    private final RestauranteRepository restauranteRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Restaurante createRestaurante(RestauranteEvent restauranteEvent) {
        Restaurante restaurante = restauranteRepository.save(restauranteEvent.getRestaurante());
        RestauranteEvent event = new RestauranteEvent("CreateRestaurante", restaurante);
        kafkaTemplate.send("restaurante-event-topic", event);
        return restaurante;
    }

    public Restaurante updateRestaurante(RestauranteEvent restauranteEvent) {
        Optional<Restaurante> existingRestauranteOpt = restauranteRepository.findById(restauranteEvent.getRestaurante().getId());
        if (existingRestauranteOpt.isPresent()) {
            Restaurante existingRestaurante = existingRestauranteOpt.get();
            Restaurante newRestaurante = restauranteEvent.getRestaurante();
            existingRestaurante.setNombre(newRestaurante.getNombre());
            existingRestaurante.setDireccion(newRestaurante.getDireccion());
            Restaurante updatedRestaurante = restauranteRepository.save(existingRestaurante);
            RestauranteEvent event = new RestauranteEvent("UpdateRestaurante", updatedRestaurante);
            kafkaTemplate.send("restaurante-event-topic", event);
            return updatedRestaurante;
        } else {
            throw new IllegalArgumentException("Restaurante no encontrado");
        }
    }
}
