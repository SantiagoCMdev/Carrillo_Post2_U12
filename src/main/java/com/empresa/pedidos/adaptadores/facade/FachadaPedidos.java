package com.empresa.pedidos.adaptadores.facade;

import com.empresa.pedidos.adaptadores.procesadores.ProcesadorPedidoFactory;
import com.empresa.pedidos.aplicacion.PedidoProcesadoEvent;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.puertos.RepositorioPedidos;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FachadaPedidos {

    private final ProcesadorPedidoFactory factory;
    private final RepositorioPedidos repositorio;
    private final ApplicationEventPublisher publisher;

    public FachadaPedidos(ProcesadorPedidoFactory factory,
                          RepositorioPedidos repositorio,
                          ApplicationEventPublisher publisher) {
        this.factory = factory;
        this.repositorio = repositorio;
        this.publisher = publisher;
    }

    public Pedido crearPedido(Pedido pedido) {
        factory.obtener(pedido.getTipo()).procesar(pedido);
        var guardado = repositorio.guardar(pedido);
        publisher.publishEvent(new PedidoProcesadoEvent(guardado));
        return guardado;
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repositorio.buscarPorId(id);
    }
}
