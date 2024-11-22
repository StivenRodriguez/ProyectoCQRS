package co.vinni.cqrs.dto;

import co.vinni.cqrs.persistence.entity.Domiciliario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomiciliarioEvent {
    private String eventType;
    private Domiciliario domiciliario;
}
