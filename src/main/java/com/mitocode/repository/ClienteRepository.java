package com.mitocode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.entity.Cliente;
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
