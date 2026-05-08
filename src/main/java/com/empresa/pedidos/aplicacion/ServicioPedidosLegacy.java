package com.empresa.pedidos.aplicacion;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.infraestructura.persistencia.RepositorioPedidosJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPedidosLegacy {

    @Autowired
    private RepositorioPedidosJpa repo;

    public void procesarPedido(Pedido pedido) {
        if (pedido.getTipo() == TipoPedido.ESTANDAR) {
            pedido.setCosto(pedido.getSubtotal() * 1.1);
        } else if (pedido.getTipo() == TipoPedido.EXPRESS) {
            pedido.setCosto(pedido.getSubtotal() * 1.3);
        } else if (pedido.getTipo() == TipoPedido.INTERNACIONAL) {
            pedido.setCosto(pedido.getSubtotal() * 1.5 + 25.0);
        }
        pedido.setEstado(EstadoPedido.PROCESADO);
        repo.save(pedido);
        System.out.println("Notificacion enviada para pedido: " + pedido.getId());
    }
}