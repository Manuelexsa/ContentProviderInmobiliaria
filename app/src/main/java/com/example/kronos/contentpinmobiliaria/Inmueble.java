package com.example.kronos.contentpinmobiliaria;

import java.io.Serializable;

/**
 * Created by kronos on 23/02/2015.
 */
public class Inmueble implements Serializable, Comparable<Inmueble> {

    /***************VARIABLES DE INSTANCIA*******************/

    private long id;
    private double precio;
    private String localidad, direccion, tipo;
    private int subido;

    /***************CONSTRUCTORES*******************/

    public Inmueble(){
        this(0,"","","",0);
    }

    public Inmueble(long id, String localidad, String direccion, String tipo, double precio){
        this.id = id;
        this.localidad = localidad;
        this.direccion = direccion;
        this.tipo = tipo;
        this.precio = precio;
        this.subido = 0;
    }

    public Inmueble(String localidad, String direccion, String tipo, String precio){
        this.localidad = localidad;
        this.direccion = direccion;
        this.tipo = tipo;
        try{
            this.precio = Double.parseDouble(precio);
        }catch (NumberFormatException e){
            this.precio = 0;
        }
        this.subido = 0;
    }


    /***************GETTERS AND SETTERS*******************/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int isSubido() {
        return subido;
    }

    public void setSubido(int subido) {
        this.subido = subido;
    }

    /***************EQUALS*******************/

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble i = (Inmueble) o;
        if (id != i.id) return false;
        return true;
    }

    /***************HASH CODE*******************/

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    /***************COMPARE TO*******************/

    @Override
    public int compareTo(Inmueble i) {
        if(this.precio!=i.precio){
            return (int)(this.precio-i.precio);
        }else if(this.localidad.compareTo(i.localidad)!=0){
            return this.localidad.compareTo(i.localidad);
        }else{
            return this.direccion.compareTo(i.direccion);
        }
    }

    /***************TO STRING*******************/

    @Override
    public String toString() {
        return "Inmueble{" +
                "id=" + id +
                ", precio=" + precio +
                ", localidad='" + localidad + '\'' +
                ", callenum='" + direccion + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}

