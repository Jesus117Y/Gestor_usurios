package com.CursoJava.Curso.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.CursoJava.Curso.models.Usuario;

import jakarta.transaction.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository 
@Transactional
public class UsuarioDaoImp implements Usuariodao {

    @PersistenceContext 
    EntityManager entityManager;
    
    @Override
    @Transactional 
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario"; // Consulta JPQL para obtener todos los usuarios
        List<Usuario> resultado = entityManager.createQuery(query, Usuario.class).getResultList(); // Ejecuta la consulta y obtiene la lista de usuarios
        // Implementación del método para obtener la lista de usuarios desde la base de datos
        return resultado; // Retorna la lista de usuarios obtenida
    }

    @Override
    public void eliminarUsuario(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id); // Busca el usuario por su ID
        if (usuario != null) { // Verifica si el usuario existe
            entityManager.remove(usuario); // Elimina el usuario de la base de datos
        }
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario); // Persiste el nuevo usuario en la base de datos
    }

    @Override
    public boolean validarUsuario(Usuario usuario) {
        String query = "FROM Usuario  WHERE email = :email AND password = :password"; // Consulta JPQL para validar el usuario
        List<Usuario> resultado = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail()) // Establece el parámetro del email
                .setParameter("password", usuario.getPassword()) // Establece el parámetro de la contraseña
                .getResultList(); // Ejecuta la consulta y obtiene la lista de usuarios que coinciden con las credenciales
        
        return !resultado.isEmpty(); // Retorna true si se encontró un usuario válido, false en caso contrario

    }



}
