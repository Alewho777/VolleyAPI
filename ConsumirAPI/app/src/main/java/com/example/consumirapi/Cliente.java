package com.example.consumirapi;

public class Cliente {
    private Long id;
    private long userId;
    private String nombre;
    private String apellido;
    private String email;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return (nombre != null) ? nombre : "";
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return (apellido != null) ? apellido : "";
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return (email != null) ? email : "";
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
