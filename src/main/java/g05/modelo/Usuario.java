package g05.modelo;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Clase Usuario
 * @author Jeremy Poveda
 * @author Angel Tomala
 * @author Paulina Loor
 * @version:
 */
public abstract class Usuario implements Serializable {
    protected String nombre;
    protected String cedula;
    protected String telefono;
    protected String email;	


    /**
     * Constructor de la clase Usuario, recibe como parametros el nombre del Usuario, telefono, email
     * @param nombre Nombre del Usuario
     * @param telefono Telefono del Usuario
     * @param email Email del Usuario
     */
    public Usuario(String nombre, String cedula, String telefono, String email){
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.email = email;
    }

    public Usuario(String cedula){
        this.cedula=cedula;
    }

    //Getters y Setters
    public String getNombre(){
        return this.nombre;
    }

    public String getTelefono(){
        return this.telefono;
    }

    public String getEmail(){
        return this.email;
    }

    public void setNombre(String n){
        this.nombre = n;
    }

    public void setTelefono(String t){
        this.telefono = t;
    }

    public void setEmail(String e){
        this.email = e;
    }

    public String getCedula() {
        return cedula;
    }

    /**
     * Muestra por pantalla la informacion
     */
    @Override
    public String toString(){
        return ">> Nombre: "+nombre+" | "+" Cédula: "+cedula+" | Teléfono: "+telefono+" | Email: "+email+" |";
    }
}