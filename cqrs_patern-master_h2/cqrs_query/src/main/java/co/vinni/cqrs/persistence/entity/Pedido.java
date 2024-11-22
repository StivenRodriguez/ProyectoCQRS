package co.vinni.cqrs.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "pedidos")
public class Pedido {
    @Id
    private String id;
    private Restaurante restaurante;
    private Usuario usuario;
    private Domiciliario domiciliario;
    private String detallePedido;
    private String estadoRestaurante;
    private String estadoDomiciliario;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}