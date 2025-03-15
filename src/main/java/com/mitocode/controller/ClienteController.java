package com.mitocode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mitocode.dto.RespuestaError;
import com.mitocode.dto.RespuestaExito;
import com.mitocode.entity.Cliente;
import com.mitocode.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	 private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	    @Autowired
	    private ClienteService clienteService;

	    
	    @PostMapping("/guardarCliente")
	    public ResponseEntity<?> guardarCliente(@RequestBody Cliente cliente) {
	        log.info("Solicitud para registrar cliente: {}", cliente);

	        try {
	            Cliente nuevoCliente = clienteService.registrarCliente(cliente);
	            String mensaje = "Cliente " + cliente.getnumDocumento() + " almacenado de forma exitosa.";
	            log.info("Registro exitoso: {}", mensaje);
	            return ResponseEntity.ok(new RespuestaExito(cliente.getnumDocumento(), mensaje));
	        } catch (IllegalArgumentException e) {
	            log.warn("Error de validación o negocio al registrar cliente: {}", e.getMessage());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new RespuestaError(cliente.getnumDocumento(), e.getMessage()));
	        } catch (Exception e) {
	            log.error("Error técnico al registrar cliente", e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(new RespuestaError(cliente.getnumDocumento(), "Error técnico: " + e.getMessage()));
	        }
	    }

	  
	    @PostMapping("/actualizarCliente")
	    public ResponseEntity<?> actualizarCliente(@RequestBody Cliente cliente) {
	        log.info("Solicitud para actualizar cliente: {}", cliente);

	        try {
	            Cliente clienteActualizado = clienteService.actualizarCliente(cliente);
	            String mensaje = "Cliente " + cliente.getnumDocumento() + " actualizado de forma exitosa.";
	            log.info("Actualización exitosa: {}", mensaje);
	            return ResponseEntity.ok(new RespuestaExito(cliente.getnumDocumento(), mensaje));
	        } catch (IllegalArgumentException e) {
	            log.warn("Error de validación o negocio al actualizar cliente: {}", e.getMessage());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new RespuestaError(cliente.getnumDocumento(), e.getMessage()));
	        } catch (Exception e) {
	            log.error("Error técnico al actualizar cliente", e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(new RespuestaError(cliente.getnumDocumento(), "Error técnico: " + e.getMessage()));
	        }
	    }

	    
	    @GetMapping("/consultarCliente/{numDocumento}")
	    public ResponseEntity<?> consultarCliente(@PathVariable String numDocumento) {
	        log.info("Solicitud para consultar cliente con ID: {}", numDocumento);

	        try {
	            Optional<Cliente> clienteOpt = clienteService.consultarCliente(numDocumento);
	            if (clienteOpt.isPresent()) {
	                log.info("Cliente encontrado: {}", clienteOpt.get());
	                return ResponseEntity.ok(clienteOpt.get());
	            } else {
	                String mensaje = "Cliente con ID " + numDocumento + " no se encuentra registrado.";
	                log.warn(mensaje);
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                        .body(new RespuestaError(numDocumento, mensaje));
	            }
	        } catch (Exception e) {
	            log.error("Error técnico al consultar cliente", e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(new RespuestaError(numDocumento, "Error técnico: " + e.getMessage()));
	        }
	    }

	    
	    @DeleteMapping("/eliminarCliente/{numDocumento}")
	    public ResponseEntity<?> eliminarCliente(@PathVariable String numDocumento) {
	        log.info("Solicitud para eliminar cliente con ID: {}", numDocumento);

	        try {
	            clienteService.eliminarCliente(numDocumento);
	            String mensaje = "Cliente con ID " + numDocumento + " eliminado exitosamente.";
	            log.info("Eliminación exitosa: {}", mensaje);
	            return ResponseEntity.ok(new RespuestaExito(numDocumento, mensaje));
	        } catch (IllegalArgumentException e) {
	            log.warn("Error de negocio al eliminar cliente: {}", e.getMessage());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body(new RespuestaError(numDocumento, e.getMessage()));
	        } catch (Exception e) {
	            log.error("Error técnico al eliminar cliente", e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(new RespuestaError(numDocumento, "Error técnico: " + e.getMessage()));
	        }
	    }
	}