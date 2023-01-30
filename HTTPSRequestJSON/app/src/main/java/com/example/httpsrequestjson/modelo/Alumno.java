package com.example.httpsrequestjson.modelo;

public class Alumno {
    private String NOMBRE;
    private String APELLIDO;
    private String id_alumnos;

    public Alumno(){

    }
    public Alumno(String NOMBRE, String APELLIDO, String id_alumnos){
        this.NOMBRE = NOMBRE;
        this.APELLIDO = APELLIDO;
        this.id_alumnos = id_alumnos;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getAPELLIDO() {
        return APELLIDO;
    }

    public void setAPELLIDO(String APELLIDO) {
        this.APELLIDO = APELLIDO;
    }

    public String getId_alumnos() {
        return id_alumnos;
    }

    public void setId_alumnos(String id_alumnos) {
        this.id_alumnos = id_alumnos;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + NOMBRE + '\'' +
                ", apellido='" + APELLIDO + '\'' +
                ", dni='" + id_alumnos + '\'' +
                '}';
    }
}
