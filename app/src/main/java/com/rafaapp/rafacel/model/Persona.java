package com.rafaapp.rafacel.model;


import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Persona {

    public int perId = -1;
    String perNombre = "";
    String perApellido = "";
    String perTelefonoFijo = "";
    String perFechaNacimiento;
    String perCedula = "";
    ArrayList<EquipoCelular> equipoCelular;

    public ArrayList<EquipoCelular> getEquipoCelular() {
        return equipoCelular;
    }

    public void setEquipoCelular(ArrayList<EquipoCelular>  equipoCelular) {
        this.equipoCelular = equipoCelular;
    }


    //Getter and Setters

    public int getPerid() {
        return perId;
    }

    public void setPerid(int perId) {
        this.perId = perId;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getPerApellido() {
        return perApellido;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public String getPerTelefonoFijo() {
        return perTelefonoFijo;
    }

    public void setPerTelefonoFijo(String perTelefonoFijo) {
        this.perTelefonoFijo = perTelefonoFijo;
    }

    public String getPerFechaNacimiento() {
        return perFechaNacimiento;
    }

    public void setPerFechaNacimiento(String perFechaNacimiento) {
        this.perFechaNacimiento = perFechaNacimiento;
    }

    public String getPerCedula() {
        return perCedula;
    }

    public void setPerCedula(String perCedula) {
        this.perCedula = perCedula;
    }

    public boolean isFullfilled() {
        return (perNombre != null && !perNombre.isEmpty())
                && (perApellido != null && !perApellido.isEmpty())
                && (perCedula!= null && !perCedula.isEmpty())
                && (perFechaNacimiento!= null)
                && (perTelefonoFijo != null && !perTelefonoFijo.isEmpty())
                && (equipoCelular != null);
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return perNombre + " " + perApellido;
    }
}
