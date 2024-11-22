package co.vinni.cqrs.dto;

import co.vinni.cqrs.persistence.entity.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteEvent {
    private String eventType;
    private Restaurante restaurante;
}

