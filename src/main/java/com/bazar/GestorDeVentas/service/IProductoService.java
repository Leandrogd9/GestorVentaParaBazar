package com.bazar.GestorDeVentas.service;

import com.bazar.GestorDeVentas.model.Producto;
import java.util.List;

public interface IProductoService {
    
    public void createProducto(Producto produ);
    
    public List<Producto> getProductos();
    
    public Producto findProducto(Long codigo_producto);
    
    public void deleteProducto(Long codigo_producto);
    
    public Producto editProducto(Long codigo_producto, String nuevoNombre, String nuevaMarca, Double nuevoCosto, Double nuevaCantidad);
    
    public List<Producto> getFaltaStock();
}
