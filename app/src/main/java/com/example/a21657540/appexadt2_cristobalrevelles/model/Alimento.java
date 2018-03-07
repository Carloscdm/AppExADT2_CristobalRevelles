package com.example.a21657540.appexadt2_cristobalrevelles.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 21657540 on 07/03/2018.
 */

public class Alimento implements Serializable{
    private String nombre;
    private String tipo;
    private String origen;
    private String nutrientes;
    private String funcion;

    private ArrayList<Alimento> alimentos;



    public Alimento(String nombre, String tipo, String origen, String nutrientes, String funcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.origen = origen;
        this.nutrientes = nutrientes;
        this.funcion = funcion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getNutrientes() {
        return nutrientes;
    }

    public void setNutrientes(String nutrientes) {
        this.nutrientes = nutrientes;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }


}
