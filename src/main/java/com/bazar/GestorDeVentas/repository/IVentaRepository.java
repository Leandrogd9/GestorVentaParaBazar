package com.bazar.GestorDeVentas.repository;

import com.bazar.GestorDeVentas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long>{
    
}
