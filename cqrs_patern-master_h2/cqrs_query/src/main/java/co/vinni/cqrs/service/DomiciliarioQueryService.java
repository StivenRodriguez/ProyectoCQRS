package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.DomiciliarioEvent;
import co.vinni.cqrs.persistence.entity.Domiciliario;
import co.vinni.cqrs.persistence.repository.DomiciliarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class DomiciliarioQueryService {
    DomiciliarioRepository domiciliarioRepository;

    public List<Domiciliario> getAll() {
        return this.domiciliarioRepository.findAll();
    }

    public Domiciliario getById(String id) {
        return this.domiciliarioRepository.findById(id).get();
    }

    @KafkaListener(topics = "domiciliario-event-topic",groupId = "domiciliario-event-group")
    public void processDomiciliarioEvents(DomiciliarioEvent domiciliarioEvent){
        Domiciliario domiciliario = domiciliarioEvent.getDomiciliario();
        if(domiciliarioEvent.getEventType().equals("CreateDomiciliario")){
            domiciliarioRepository.save(domiciliario);
        }
        if(domiciliarioEvent.getEventType().equals("UpdateDomiciliario")){
            Domiciliario existingDomiciliario = domiciliarioRepository.findById(domiciliario.getId()).get();
            existingDomiciliario.setNombre(domiciliario.getNombre());
            existingDomiciliario.setTelefono(domiciliario.getTelefono());

            domiciliarioRepository.save(existingDomiciliario);
        }
    }
}
