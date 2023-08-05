package com.bazar.GestorDeVentas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MayorVentaDTO {
    
    private Long codigo_venta;
    private Double total;
    private Integer cantidad_producto;
    private String nombre_cliente;
    private String apellido_cliente;

    public MayorVentaDTO() {
    }

    public MayorVentaDTO(Long codigo_venta, Double total, Integer cantidad_producto, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantidad_producto = cantidad_producto;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }
}
