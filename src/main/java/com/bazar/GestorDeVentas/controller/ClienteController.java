package com.bazar.GestorDeVentas.controller;

import com.bazar.GestorDeVentas.model.Cliente;
import com.bazar.GestorDeVentas.service.IClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @Autowired
    private IClienteService cliServi;
    
    @PostMapping("/clientes/crear")
    public String createCliente(@RequestBody Cliente cli){

        try{
            cliServi.createCliente(cli);
            
            return "El cliente se creo correctamente.";
        }catch(Exception e){
            return "No se pudo guarda el cliente porque faltan datos.";
        }  
    }
    
    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
    
        return cliServi.getClientes();
    }
    
    @GetMapping("/clientes/{id_cliente}")
    public Cliente findCliente(@PathVariable Long id_cliente){
        return cliServi.findCliente(id_cliente);
    }
    
    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String deleteCliente(@PathVariable Long id_cliente){
        
        cliServi.deleteCliente(id_cliente);
        
        return "El cliente se elimino correctamente.";
    }
    
    @PutMapping("/clientes/editar/{id_cliente}")
    public Cliente editCliente(@PathVariable Long id_cliente,
                                 @RequestParam(required = false, name = "nombre") String nuevoNombre, 
                                 @RequestParam(required = false, name = "apellido") String nuevoApellido,
                                 @RequestParam(required = false, name = "dni") String nuevoDni){
        
        Cliente cli = cliServi.editCliente(id_cliente, nuevoNombre, nuevoApellido, nuevoDni);
                
        return cli;
    }
}
