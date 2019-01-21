package com.notas.resfull.repository;

import com.notas.resfull.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("gestorUsuario")
public interface GestorUsuario extends JpaRepository<Usuario , Serializable> {
    public abstract Usuario findByUsuario(String usuario);
}
