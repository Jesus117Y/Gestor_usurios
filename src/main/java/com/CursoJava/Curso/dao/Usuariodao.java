package com.CursoJava.Curso.dao;
import com.CursoJava.Curso.models.Usuario;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface Usuariodao {

    List<Usuario> getUsuarios();

    void eliminarUsuario(Long id);

    void registrar(Usuario usuario); 

    boolean exiteUsuario(Usuario usuario); // Método para verificar si el usuario existe

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

}
