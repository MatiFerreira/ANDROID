package com.example.recyclerview.modelos;

public class Movie {
    private String name;
    private int imagen;

    public Movie(){

    }
    public Movie(String nombre,int imagen){
        this.imagen = imagen;
        this.name = nombre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
