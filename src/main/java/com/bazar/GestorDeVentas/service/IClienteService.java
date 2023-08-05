package com.bazar.GestorDeVentas.service;

import com.bazar.GestorDeVentas.model.Cliente;
import java.util.List;

public interface IClienteService {
    
    public void createCliente(Cliente cli);
    
    public List<Cliente> getClientes();
    
    public Cliente findCliente(Long id_cliente);
    
    public void deleteCliente(Long id_cliente);
    
    public Cliente editCliente(Long id_cliente, String nombre, String apellido, String dni);
}
