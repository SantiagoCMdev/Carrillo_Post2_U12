package com.empresa.pedidos.dominio;
import com.empresa.pedidos.infraestructura.persistencia.RepositorioPedidosJpa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoPedido tipo;
    private EstadoPedido estado;
    private Double subtotal;
    private Double costo;

    public Pedido(TipoPedido tipo, Double subtotal) {
        this.tipo = tipo;
        this.subtotal = subtotal;
        this.estado = EstadoPedido.PENDIENTE;
    }
    // Violacion intencional - usar la clase de infraestructura
    private transient RepositorioPedidosJpa violacionIntencional;
}
