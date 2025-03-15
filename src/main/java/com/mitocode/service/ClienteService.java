package com.mitocode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.entity.Cliente;
import com.mitocode.repository.ClienteRepository;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ClienteService {
	
	
	@Autowired
    private ClienteRepository clienteRepository;

   
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

   
    @Transactional
    public Cliente registrarCliente(Cliente cliente) throws IllegalArgumentException {
        validarCliente(cliente);

        if (clienteRepository.existsById(cliente.getnumDocumento())) {
            throw new IllegalArgumentException("Cliente " + cliente.getnumDocumento() + " ya se encuentra registrado.");
        }

        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> consultarCliente(String numDocumento) {
        return clienteRepository.findById(numDocumento);
    }

    @Transactional
    public Cliente actualizarCliente(Cliente cliente) throws IllegalArgumentException {
        validarCliente(cliente);

        if (!clienteRepository.existsById(cliente.getnumDocumento())) {
            throw new IllegalArgumentException("Cliente " + cliente.getnumDocumento() + " no se encuentra registrado.");
        }

        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(String numDocumento) throws IllegalArgumentException {
        if (!clienteRepository.existsById(numDocumento)) {
            throw new IllegalArgumentException("Cliente " + numDocumento + " no se encuentra registrado.");
        }

        clienteRepository.deleteById(numDocumento);
    }

    private void validarCliente(Cliente cliente) throws IllegalArgumentException {
        StringBuilder errores = new StringBuilder();

        if (cliente.getTipoDocumento() == null || cliente.getTipoDocumento().isEmpty()) {
            errores.append("Campo tipoDocumento es obligatorio. ");
        }
        if (cliente.getnumDocumento() == null || cliente.getnumDocumento().isEmpty()) {
            errores.append("Campo numDocumento es obligatorio. ");
        }
        if (cliente.getPrimerNombre() == null || cliente.getPrimerNombre().isEmpty()) {
            errores.append("Campo primerNombre es obligatorio. ");
        }
        if (cliente.getPrimerApellido() == null || cliente.getPrimerApellido().isEmpty()) {
            errores.append("Campo primerApellido es obligatorio. ");
        }
        if (cliente.getTelefono() == null) {
            errores.append("Campo teléfono es obligatorio. ");
        }
        if (cliente.getCorreElectronico() == null || !EMAIL_PATTERN.matcher(cliente.getCorreElectronico()).matches()) {
            errores.append("Campo correElectronico no cumple con la estructura de un correo válido.");
        }

        if (errores.length() > 0) {
            throw new IllegalArgumentException(errores.toString());
        }
    }
}


	    