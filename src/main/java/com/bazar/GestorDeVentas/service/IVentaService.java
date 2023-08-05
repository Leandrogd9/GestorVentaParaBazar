package com.bazar.GestorDeVentas.service;

import com.bazar.GestorDeVentas.dto.MayorVentaDTO;
import com.bazar.GestorDeVentas.model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    
    public void createVenta(Venta venta);
    
    public List<Venta> getVentas();
    
    public Venta findVenta(Long codigo_venta);
    
    public void deleteVenta(Long codigo_venta);
    
    public Venta editVenta(Long codigo_venta, LocalDate fecha_venta, Double total);
    
    public Venta findVentaProducto(Long codigo_venta);
    
    public String findVentasPorfecha(LocalDate fecha);
    
    public MayorVentaDTO findMayorVenta ();
}
