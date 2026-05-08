package com.empresa.pedidos.dominio.puertos;

import com.empresa.pedidos.aplicacion.PedidoProcesadoEvent;

public interface ServicioNotificacion {
    void notificar(PedidoProcesadoEvent evento);
}