package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.RestauranteEvent;
import co.vinni.cqrs.persistence.entity.Restaurante;
import co.vinni.cqrs.persistence.repository.RestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class RestauranteQueryService {
    RestauranteRepository restauranteRepository;

    public List<Restaurante> getAll() {
        return this.restauranteRepository.findAll();
    }

    public Restaurante getById(String id) {
        return this.restauranteRepository.findById(id).get();
    }

    @KafkaListener(topics = "restaurante-event-topic",groupId = "restaurante-event-group")
    public void processRestauranteEvents(RestauranteEvent restauranteEvent){

        try {
            Restaurante restaurante =  restauranteEvent.getRestaurante();
            if(restauranteEvent.getEventType().equals("CreateRestaurante")) {
                restauranteRepository.save(restaurante);
            }
            if(restauranteEvent.getEventType().equals("UpdateRestaurante")){
                Restaurante existingRestaurante = restauranteRepository.findById(restaurante.getId()).get();
                existingRestaurante.setNombre(restaurante.getNombre());
                existingRestaurante.setDireccion(restaurante.getDireccion());

                restauranteRepository.save(existingRestaurante);
            }
        } catch (Exception ex){

        }
    }
}
