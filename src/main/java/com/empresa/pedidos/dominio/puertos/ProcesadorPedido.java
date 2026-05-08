package com.empresa.pedidos.dominio.puertos;

import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;

public interface ProcesadorPedido {
    TipoPedido getTipo();
    void procesar(Pedido pedido);
}
