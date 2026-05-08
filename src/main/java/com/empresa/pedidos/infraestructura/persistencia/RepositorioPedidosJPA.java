package com.empresa.pedidos.infraestructura.persistencia;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioPedidosJpa
        extends JpaRepository<Pedido, Long>, RepositorioPedidos {

    default Pedido guardar(Pedido pedido) {
        return save(pedido);
    }

    default Optional<Pedido> buscarPorId(Long id) {
        return findById(id);
    }
}
