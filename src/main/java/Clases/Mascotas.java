package Clases;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Mascotas {
    int id, idPropietario;
    String nombre,raza;
    double peso;
    Date fechaNacimiento;
    String genero;

    public Mascotas(int id, int idPropietario, String nombre,String raza, double peso, Date fechaNacimiento,String genero) {
        this.id = id;
        this.idPropietario = idPropietario;
        this.nombre = nombre;
        this.raza = raza;
        this.peso = peso;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public StringProperty nombreProperty(){
        StringProperty nombre1 = new SimpleStringProperty(this.nombre);
        return nombre1;
    }

    @Override
    public String toString() {
        return "ID = "+id+" Mascota: "+nombre;
    }
}
