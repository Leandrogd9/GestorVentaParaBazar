package com.bazar.GestorDeVentas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "Venta")
public class Venta {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    @ManyToMany
    @JoinTable(name = "Venta_Producto",
               joinColumns = @JoinColumn(name = "FK_Venta", nullable = false),
               inverseJoinColumns = @JoinColumn(name = "FK_Producto", nullable = false))
    @JsonIgnoreProperties("listaVentas")
    private List<Producto> listaProductos;
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnoreProperties("listaVentas")
    private Cliente unCliente;
    
    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }
    
}
