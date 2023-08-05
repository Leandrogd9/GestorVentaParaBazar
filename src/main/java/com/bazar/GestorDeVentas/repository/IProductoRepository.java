package com.bazar.GestorDeVentas.repository;

import com.bazar.GestorDeVentas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{
    
}
