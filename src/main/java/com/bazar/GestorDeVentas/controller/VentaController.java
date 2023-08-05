package com.bazar.GestorDeVentas.controller;

import com.bazar.GestorDeVentas.dto.MayorVentaDTO;
import com.bazar.GestorDeVentas.model.Producto;
import com.bazar.GestorDeVentas.model.Venta;
import com.bazar.GestorDeVentas.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {
    @Autowired
    private IVentaService ventaServi;
    
    @PostMapping("/ventas/crear")
    public ResponseEntity<String> createVenta(@RequestBody Venta venta){
        
        if(venta.getListaProductos() == null || venta.getListaProductos().isEmpty() == true){
            return ResponseEntity.badRequest().body("Algun parametro esta incompleto.");
        }
        
        ventaServi.createVenta(venta);
        
        return ResponseEntity.ok("La venta se creo correctamente.");
    }
    
    @GetMapping("/ventas")
    public ResponseEntity<List<Venta>> getVentas(){
        return ResponseEntity.ok().body(ventaServi.getVentas());
    }
    
    @GetMapping("/ventas/{codigo_venta}")
    public ResponseEntity<Venta> findVenta(@PathVariable Long codigo_venta){
        return ResponseEntity.ok().body(ventaServi.findVenta(codigo_venta));
    }
    
    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long codigo_venta){
        ventaServi.deleteVenta(codigo_venta);
        return ResponseEntity.ok().body("La venta se elimino correctamente.");
    }
    
    @PutMapping("/ventas/editar/{codigo_venta}")
    public ResponseEntity<Venta> editVenta(@PathVariable Long codigo_venta,
                                           @RequestParam(required = false, name = "fecha_venta") LocalDate nuevaFecha_venta, 
                                           @RequestParam(required = false, name = "total") Double nuevoTotal){
        return ResponseEntity.ok().body(ventaServi.editVenta(codigo_venta, nuevaFecha_venta, nuevoTotal));
    }
    
    @GetMapping("/ventas/productos/{codigo_venta}")
    public ResponseEntity<List<Producto>> findVentaProducto(@PathVariable Long codigo_venta){
        Venta venta = ventaServi.findVentaProducto(codigo_venta);
        
        if(venta == null){
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(venta.getListaProductos());
    }
    
    @GetMapping("/ventas/fecha/{fecha_venta}")
    public ResponseEntity<String> findVentasPorfecha(@PathVariable LocalDate fecha_venta){
        
        if(fecha_venta.isAfter(LocalDate.now())){
            return ResponseEntity.badRequest().body("La fecha todavia no paso.");
        }
        
        return ResponseEntity.ok(ventaServi.findVentasPorfecha(fecha_venta));
    }
    
    @GetMapping("/ventas/mayor_venta")
    public ResponseEntity<MayorVentaDTO> findMayorVenta(){

        return ResponseEntity.ok(ventaServi.findMayorVenta());
    }
}
