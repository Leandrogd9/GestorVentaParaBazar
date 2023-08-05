package com.bazar.GestorDeVentas.service;

import com.bazar.GestorDeVentas.model.Cliente;
import com.bazar.GestorDeVentas.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
    
    @Autowired
    private IClienteRepository cliRepo;
    
    @Override
    public void createCliente(Cliente cli) {
        cliRepo.save(cli);
    }

    @Override
    public List<Cliente> getClientes() {
        return cliRepo.findAll();
    }

    @Override
    public Cliente findCliente(Long id_cliente) {
        return cliRepo.findById(id_cliente).orElse(null);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        cliRepo.deleteById(id_cliente);
    }

    @Override
    public Cliente editCliente(Long id_cliente, String nuevoNombre, String nuevoApellido, String nuevoDni) {
        Cliente cli = this.findCliente(id_cliente);
        
        cli.setNombre(nuevoNombre);
        cli.setApellido(nuevoApellido);
        cli.setDni(nuevoDni);
        
        this.createCliente(cli);
        
        return cli;
    }
    
}
