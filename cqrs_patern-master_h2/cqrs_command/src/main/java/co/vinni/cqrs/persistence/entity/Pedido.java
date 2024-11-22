package co.vinni.cqrs.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Pedido_Usuario"))
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = true, foreignKey = @ForeignKey(name = "FK_Pedido_Restaurante"))
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "domiciliario_id", nullable = true, foreignKey = @ForeignKey(name = "FK_Pedido_Domiciliario"))
    private Domiciliario domiciliario;

    @Column(name = "estado_restaurante", length = 20, nullable = true)
    private String estadoRestaurante;

    @Column(name = "detalle_pedido")
    private String detallePedido;

    @Column(name = "estado_domiciliario", length = 20, nullable = true)
    private String estadoDomiciliario;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PreUpdate
    public void preUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
}
