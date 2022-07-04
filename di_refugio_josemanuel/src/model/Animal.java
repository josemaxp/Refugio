package model;

import java.time.LocalDate;

/**
 * Class for animal
 * @author josem
 */
public class Animal {

    int id;
    String nombre;
    Character sexo;
    LocalDate fechaNacimiento;
    String color;
    String raceName;
    Double peso;
    LocalDate fechaLlegada;
    LocalDate fechaAdopcon;
    String caracteristicas;

    public Animal(int id, String nombre, Character sexo, LocalDate fechaNacimiento, String color, String race, Double peso, LocalDate fechaLlegada, LocalDate fechaAdopcon, String caracteristicas) {
        this.id = id;
        this.nombre = nombre;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.color = color;
        this.raceName = race;
        this.peso = peso;
        this.fechaLlegada = fechaLlegada;
        this.fechaAdopcon = fechaAdopcon;
        this.caracteristicas = caracteristicas;
    }

    public Animal(String nName, Character nSex, LocalDate nBornDate, String nColor, String nRace, Double nWeigth, String nDescription) {
        this.nombre = nName;
        this.sexo = nSex;
        this.fechaNacimiento = nBornDate;
        this.color = nColor;
        this.raceName = nRace;
        this.peso = nWeigth;
        this.caracteristicas = nDescription;
    }
    
    public Animal(int id, String nName, Character nSex, LocalDate nBornDate, String nColor, String nRace, Double nWeigth, String nDescription) {
        this.id = id;
        this.nombre = nName;
        this.sexo = nSex;
        this.fechaNacimiento = nBornDate;
        this.color = nColor;
        this.raceName = nRace;
        this.peso = nWeigth;
        this.caracteristicas = nDescription;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIdRaza() {
        return raceName;
    }

    public void setIdRaza(String raceName) {
        this.raceName = raceName;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalDate getFechaAdopcon() {
        return fechaAdopcon;
    }

    public void setFechaAdopcon(LocalDate fechaAdopcon) {
        this.fechaAdopcon = fechaAdopcon;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    
    public String toString(){
        return nombre;
    }

}
