package com.empresa.pedidos;

import com.empresa.pedidos.adaptadores.procesadores.*;
import com.empresa.pedidos.dominio.EstadoPedido;
import com.empresa.pedidos.dominio.Pedido;
import com.empresa.pedidos.dominio.TipoPedido;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcesadorPedidoFactoryTest {

    private ProcesadorPedidoFactory factory;

    @BeforeEach
    void setUp() {
        factory = new ProcesadorPedidoFactory(List.of(
                new ProcesadorPedidoEstandar(),
                new ProcesadorPedidoExpress(),
                new ProcesadorPedidoInternacional()
        ));
    }

    @Test
    void obtener_tipoEstandar_retornaImplementacionCorrecta() {
        ProcesadorPedido p = factory.obtener(TipoPedido.ESTANDAR);
        assertEquals(TipoPedido.ESTANDAR, p.getTipo());
    }

    @Test
    void obtener_tipoExpress_retornaImplementacionCorrecta() {
        ProcesadorPedido p = factory.obtener(TipoPedido.EXPRESS);
        assertEquals(TipoPedido.EXPRESS, p.getTipo());
    }

    @Test
    void obtener_tipoInternacional_retornaImplementacionCorrecta() {
        ProcesadorPedido p = factory.obtener(TipoPedido.INTERNACIONAL);
        assertEquals(TipoPedido.INTERNACIONAL, p.getTipo());
    }

    @Test
    void procesar_estandar_calculaCostoCorrectamente() {
        Pedido pedido = new Pedido(TipoPedido.ESTANDAR, 100.0);
        factory.obtener(TipoPedido.ESTANDAR).procesar(pedido);
        assertEquals(110.0, pedido.getCosto(), 0.001);
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }

    @Test
    void procesar_express_calculaCostoCorrectamente() {
        Pedido pedido = new Pedido(TipoPedido.EXPRESS, 100.0);
        factory.obtener(TipoPedido.EXPRESS).procesar(pedido);
        assertEquals(130.0, pedido.getCosto(), 0.001);
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }

    @Test
    void procesar_internacional_calculaCostoCorrectamente() {
        Pedido pedido = new Pedido(TipoPedido.INTERNACIONAL, 100.0);
        factory.obtener(TipoPedido.INTERNACIONAL).procesar(pedido);
        assertEquals(175.0, pedido.getCosto(), 0.001);
        assertEquals(EstadoPedido.PROCESADO, pedido.getEstado());
    }

    @Test
    void obtener_tipoDesconocido_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class,
                () -> factory.obtener(null));
    }
}
