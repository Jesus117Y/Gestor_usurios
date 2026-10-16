package com.CursoJava.Curso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.CursoJava.Curso.dao.Usuariodao;
import com.CursoJava.Curso.models.Usuario;

@RestController 
public class AuthController {

    @Autowired 
    private Usuariodao Usuariodao;

    @RequestMapping (value = "api/validarUsuario", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
        //Usuariodao.vericarUsuario(usuario);
        if (Usuariodao.validarUsuario(usuario)){
            return "Inicio de sesión exitoso";
        }
        return "Credenciales inválidas";
        // TODO: Validate credentials once the usuariodao dependency is injected.
        // Aquí puedes implementar la lógica para validar el usuario
        // Por ejemplo, podrías llamar a un método en tu capa de servicio para verificar las credenciales del usuario
        // usuarioService.validarUsuario(usuario.getEmail(), usuario.getPassword());

        // Retorna un mensaje de éxito o error según corresponda
        // return "Inicio de sesión exitoso"; // Cambia esto según tu lógica de validación
    }

}
