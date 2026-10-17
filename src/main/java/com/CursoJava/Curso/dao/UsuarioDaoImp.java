package com.CursoJava.Curso.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.CursoJava.Curso.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario  WHERE email = :email "; // Consulta JPQL para validar el usuario
        List<Usuario> resultado = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail()) // Establece el parámetro del email
                .getResultList(); // Ejecuta la consulta y obtiene la lista de usuarios que coinciden con las credenciales
        
        if (resultado.isEmpty()) { // Verifica si no se encontró ningún usuario
            return null; // Retorna false si no se encontró un usuario válido
        }

        String hashedPassword = resultado.get(0).getPassword(); // Obtiene la contraseña hasheada del primer usuario encontrado

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id); // Crea una instancia de Argon2 para verificar la contraseña
        if( argon2.verify(hashedPassword, usuario.getPassword())){
            return lista.get(0);
        }
        return null;

        //return !resultado.isEmpty() && passWValida; // Retorna true si se encontró un usuario válido y la contraseña es correcta, false en caso contrario

    }

   



}
