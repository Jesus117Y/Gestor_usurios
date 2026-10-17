package com.CursoJava.Curso.controllers;

import java.util.List;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import com.CursoJava.Curso.dao.Usuariodao;
import com.CursoJava.Curso.models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired 
    private Usuariodao usuarioDao;

    @RequestMapping(value = "api/selectUsuarios")
    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }

    @DeleteMapping(value = "api/eliminarUsuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id) {
        usuarioDao.eliminarUsuario(id);
        return null;
    }

    @RequestMapping(value = "api/registrarUsuario", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody  Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPassword = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hashedPassword);
        usuarioDao.registrar(usuario);

        // Aquí puedes implementar la lógica para registrar un nuevo usuario
        // Por ejemplo, podrías llamar a un método en tu capa de servicio para guardar el usuario en la base de datos
        // usuarioService.registrarUsuario(usuario);
    }   

    public boolean exiteUsuario(Usuario usuario){
        long cantidad = entityManager.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class)
                .setParameter("email", usuario.getEmail())
                .getSingleResult();
                return cantidad > 0;
    }



}
