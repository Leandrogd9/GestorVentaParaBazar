package com.bazar.GestorDeVentas.controller;

import com.bazar.GestorDeVentas.model.Producto;
import com.bazar.GestorDeVentas.service.IProductoService;
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
public class ProductoController {
    
    @Autowired
    private IProductoService produServi;
    
    @PostMapping("/productos/crear")
    public String createProducto(@RequestBody Producto produ){
        
        produServi.createProducto(produ);
        
        return "El producto se creo correctamente.";
    }
    
    @GetMapping("/productos")
    public List<Producto> getProductos(){
    
        return produServi.getProductos();
    }
    
    @GetMapping("/productos/{codigo_producto}")
    public Producto findProducto(@PathVariable Long codigo_producto){
        return produServi.findProducto(codigo_producto);
    }
    
    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public String deleteProducto(@PathVariable Long codigo_producto){
        
        produServi.deleteProducto(codigo_producto);
        
        return "El producto se elimino correctamente.";
    }
    
    @PutMapping("/productos/editar/{codigo_producto}")
    public Producto editProducto(@PathVariable Long codigo_producto,
                                 @RequestParam(required = false, name = "nombre") String nuevoNombre, 
                                 @RequestParam(required = false, name = "marca") String nuevaMarca,
                                 @RequestParam(required = false, name = "costo") Double nuevoCosto,
                                 @RequestParam(required = false, name = "cantidad_disponible") Double nuevaCantidad){
        
        Producto produ = produServi.editProducto(codigo_producto, nuevoNombre, nuevaMarca, nuevoCosto, nuevaCantidad);
                
        return produ;
    }
    
    @GetMapping("/productos/falta_stock")
    public ResponseEntity<List<Producto>> getFaltaStock(){
        
        List<Producto> listaProductos = produServi.getFaltaStock();
        
        if(listaProductos.isEmpty() == true){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaProductos);
    }
}
