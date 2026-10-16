package com.CursoJava.Curso.dao;
import com.CursoJava.Curso.models.Usuario;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface Usuariodao {

    List<Usuario> getUsuarios();

    void eliminarUsuario(Long id);

    void registrar(Usuario usuario); 
    
    boolean validarUsuario(Usuario usuario); // Método para validar el usuario

}
