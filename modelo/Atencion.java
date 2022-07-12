package modelo;
import java.time.*;
import java.util.Scanner;
import javax.xml.datatype.Duration;
import usuario.*;

/**
 * Clase que define los datos y métodos que intervienen en la Atencion del Centro
 * Representa una atención registrada en el Centro terapeutico
 * @author: 
 * @version: 11/07/2022
 */
public class Atencion{
    private Duration duracionReal;
    private Empleado empleado;
    private Servicio servicio;
    static Scanner sc3=new Scanner(System.in);
    /**
     * Constructor de la clase Atencion
     * Representa una atención que se registra en el Centro Terapeutico, la crea recibiendo los datos de la misma
     * @param duracionReal La duración ya sea mayor o menor que tuvo la atención
     * @param servicio El servicio prestado en la atención
     * @param empleado El empleado que prestó el servicio
     */
    public Atencion(Duration duracionReal, Servicio servicio, Empleado empleado){
        this.duracionReal = duracionReal;
        this.servicio = servicio;
        this.empleado = empleado;
    }
    //Métodos de la clase
    /**
     * Método que muestra el menú a presentarse en la opción de Atención
     */
    public static void mostrarMenu(){
        System.out.print("1. Registrar Atención\n");         
	    System.out.print("2. Consultar Atención\n");
    }
    public static void registrarAtencion(){
        System.out.print("Ingrese cedula del Cliente: ");
        String ced=sc3.nextLine();
        System.out.print("Ingrese nombre del Cliente: ");
        String nom=sc3.nextLine();
        Cliente c1=new Cliente(ced, nom);

        for(Cita c: Cita.getListaCitas()){
            
        }


    }
    public static void consultarAtencion(){
    }
    //Getters y setters
    public Duration getDuracionReal(){
        return this.duracionReal;
    }
    public void setDuracionR(Duration t){
        this.duracionReal = t;
    }
}