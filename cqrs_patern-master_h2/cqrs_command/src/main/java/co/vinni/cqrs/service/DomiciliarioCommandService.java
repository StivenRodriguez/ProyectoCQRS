package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.DomiciliarioEvent;
import co.vinni.cqrs.persistence.entity.Domiciliario;
import co.vinni.cqrs.persistence.repository.DomiciliarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DomiciliarioCommandService {

    private final DomiciliarioRepository domiciliarioRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Domiciliario createDomiciliario(DomiciliarioEvent domiciliarioEvent) {
        Domiciliario domiciliario = domiciliarioRepository.save(domiciliarioEvent.getDomiciliario());
        DomiciliarioEvent event = new DomiciliarioEvent("CreateDomiciliario", domiciliario);
        kafkaTemplate.send("domiciliario-event-topic", event);
        return domiciliario;
    }

    public Domiciliario updateDomiciliario(DomiciliarioEvent domiciliarioEvent) {
        Optional<Domiciliario> existingDomiciliarioOpt = domiciliarioRepository.findById(domiciliarioEvent.getDomiciliario().getId());
        if (existingDomiciliarioOpt.isPresent()) {
            Domiciliario existingDomiciliario = existingDomiciliarioOpt.get();
            Domiciliario newDomiciliario = domiciliarioEvent.getDomiciliario();
            existingDomiciliario.setNombre(newDomiciliario.getNombre());
            existingDomiciliario.setTelefono(newDomiciliario.getTelefono());
            Domiciliario updatedDomiciliario = domiciliarioRepository.save(existingDomiciliario);
            DomiciliarioEvent event = new DomiciliarioEvent("UpdateDomiciliario", updatedDomiciliario);
            kafkaTemplate.send("domiciliario-event-topic", event);
            return updatedDomiciliario;
        } else {
            throw new IllegalArgumentException("Domiciliario no encontrado");
        }
    }
}
