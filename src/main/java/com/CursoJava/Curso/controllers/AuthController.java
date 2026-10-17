package com.CursoJava.Curso.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.CursoJava.Curso.dao.Usuariodao;
import com.CursoJava.Curso.models.Usuario;
import com.CursoJava.Curso.utils.JWTUtils;

@RestController 
public class AuthController {

    @Autowired 
    private Usuariodao Usuariodao;

    @Autowired 
    private JWTUtils jwtUtils;

    @RequestMapping (value = "api/validarUsuario", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
        //Usuariodao.vericarUsuario(usuario);

        Usuario ingresar = Usuariodao.obtenerUsuarioPorCredenciales(usuario);
        if (ingresar != null){
            
            String tokenJWT = jwtUtils.create(String.valueOf(ingresar.getId()), ingresar.getEmail() );

            return tokenJWT;
        }
    }

}
