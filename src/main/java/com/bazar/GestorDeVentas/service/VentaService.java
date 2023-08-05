package com.bazar.GestorDeVentas.service;

import com.bazar.GestorDeVentas.dto.MayorVentaDTO;
import com.bazar.GestorDeVentas.model.Venta;
import com.bazar.GestorDeVentas.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    
    @Autowired
    private IVentaRepository ventaRepo;
    
    @Override
    public void createVenta(Venta venta) {

        ventaRepo.save(venta);
    }

    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta findVenta(Long codigo_venta) {
        return ventaRepo.findById(codigo_venta).orElse(null);
    }

    @Override
    public void deleteVenta(Long codigo_venta) {
        ventaRepo.deleteById(codigo_venta);
    }

    @Override
    public Venta editVenta(Long codigo_venta, LocalDate nuevaFecha_venta, Double nuevoTotal) {
        Venta venta = this.findVenta(codigo_venta);
        
        venta.setFecha_venta(nuevaFecha_venta);
        venta.setTotal(nuevoTotal);
        
        this.createVenta(venta);
        
        return venta;
    }

    @Override
    public Venta findVentaProducto(Long codigo_venta) {
        return this.findVenta(codigo_venta);
    }

    @Override
    public String findVentasPorfecha(LocalDate fecha) {
        List<Venta> listaVenta = this.getVentas();
        Double monto_total = 0.0;
        int cant_ventas = 0;
        
        for(Venta venta : listaVenta){
            if(venta.getFecha_venta().equals(fecha)){
                monto_total = monto_total + venta.getTotal();
                cant_ventas++;
            }
        }
        
        return "La cantidad de ventas en el dia " + fecha + " fueron de " + cant_ventas + " ventas y se recaudo un total de $" + monto_total; 
    }

    @Override
    public MayorVentaDTO findMayorVenta() {
        MayorVentaDTO mayorVenta = new MayorVentaDTO();
        List<Venta> listaVentas = this.getVentas();
        Double total = 0.0;
        
        for(Venta venta : listaVentas){
            if(venta.getTotal() > total){
                total = venta.getTotal();
                
                mayorVenta.setCodigo_venta(venta.getCodigo_venta());
                mayorVenta.setTotal(venta.getTotal());
                mayorVenta.setCantidad_producto(venta.getListaProductos().size());
                mayorVenta.setNombre_cliente(venta.getUnCliente().getNombre());
                mayorVenta.setApellido_cliente(venta.getUnCliente().getApellido());
            }
        }
        
        return mayorVenta;
    }
    
}
