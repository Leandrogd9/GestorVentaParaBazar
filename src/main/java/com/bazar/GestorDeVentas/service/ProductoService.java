package com.bazar.GestorDeVentas.service;

import com.bazar.GestorDeVentas.model.Producto;
import com.bazar.GestorDeVentas.repository.IProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    
    @Autowired
    private IProductoRepository produRepo;
            
    @Override
    public void createProducto(Producto produ) {
        produRepo.save(produ);
    }

    @Override
    public List<Producto> getProductos() {
        return produRepo.findAll();
    }

    @Override
    public Producto findProducto(Long codigo_producto) {
        return produRepo.findById(codigo_producto).orElse(null);
    }

    @Override
    public void deleteProducto(Long codigo_producto) {
        produRepo.deleteById(codigo_producto);
    }

    @Override
    public Producto editProducto(Long codigo_producto, String nuevoNombre, String nuevaMarca, Double nuevoCosto, Double nuevaCantidad) {
        Producto produ = this.findProducto(codigo_producto);
        
        produ.setNombre(nuevoNombre);
        produ.setMarca(nuevaMarca);
        produ.setCosto(nuevoCosto);
        produ.setCantidad_disponible(nuevaCantidad);
        
        this.createProducto(produ);
        
        return produ;
    }

    @Override
    public List<Producto> getFaltaStock() {
        List<Producto> listaProductos = this.getProductos();
        
        for(int i = listaProductos.size()-1; i >= 0; i--){
            if(listaProductos.get(i).getCantidad_disponible() >= 5){
                listaProductos.remove(i);
            }
        }
        
        return listaProductos;
    }
}
