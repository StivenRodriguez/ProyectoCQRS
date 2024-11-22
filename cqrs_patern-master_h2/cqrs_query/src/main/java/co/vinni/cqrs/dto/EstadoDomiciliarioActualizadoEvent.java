package co.vinni.cqrs.dto;

import co.vinni.cqrs.persistence.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDomiciliarioActualizadoEvent {
    private String eventType = "EstadoDomiciliarioActualizado";
    private Pedido pedido;
}
