package com.empresa.pedidos.adaptadores.procesadores;

import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import org.springframework.stereotype.Component;

@Component
public class ProcesadorPedidoExpress implements ProcesadorPedido {
    @Override public TipoPedido getTipo() { return TipoPedido.EXPRESS; }
    @Override
    public void procesar(Pedido pedido) {
        pedido.setCosto(pedido.getSubtotal() * 1.3);
        pedido.setEstado(EstadoPedido.PROCESADO);
    }
}
