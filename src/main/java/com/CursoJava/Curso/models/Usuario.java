package com.CursoJava.Curso.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Table (name = "usuario")
public class Usuario {

    @Getter @Setter @Column (name = "nombre")
    private String nombre;
    @Getter @Setter @Column (name = "apellido")
    private String apellido;
    @Getter @Setter @Column (name = "email")
    private String email;
    @Getter @Setter @Column (name = "telefono")
    private String telefono;
    @Getter @Setter @Column (name = "password")
    private String password;
    @Getter @Setter @Column (name = "id")
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    protected Usuario() {
    }

    public Usuario(String id, String nombre, String apellido, String email, String telefono, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }

}
