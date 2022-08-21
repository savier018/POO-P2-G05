package g05.controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.AccessController;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;

import g05.App;
import g05.controlador.actividad.ActividadesController;
import g05.controlador.atencion.RegistrarAtencionController;
import g05.modelo.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JuegoController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label label0;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label label5;
    @FXML
    private Label label6;
    @FXML
    private Label label7;
    @FXML
    private Label label8;
    @FXML
    private Label label9;
    @FXML
    private Label label10;
    @FXML
    private Label label11;
    @FXML
    private Label label12;
    @FXML
    private Label label13;
    @FXML
    private Label label14;
    @FXML
    private Label label15;
    @FXML
    private Label label16;
    @FXML
    private Label label17;
    @FXML
    private Label label18;
    @FXML
    private Label label19;

    @FXML
    private Label numero;

    @FXML
    private Label aciertos;

    @FXML
    private Label errores;

    @FXML
    private Label timer;



    /*
    *
    * Datos para registrar la actividad
    *
    */
    Cita citaAtendida;

    int numAciertos;
    int numErrores;
    LocalTime tiempo = LocalTime.of(0,0,0);;



    ArrayList<Integer> nms = JuegoController.generarNumeros();

    ArrayList<Integer> indicesV = indicesVacios();
    ArrayList<Integer> numerosReales = new ArrayList<>();
    HashMap<String, Label> labels = new HashMap<>();
    SoundController sc = new SoundController();
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
        tiempo = tiempo.plusSeconds(1);
        timer.setText(tiempo.toString());
    }));


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        labels.put("label0", label0);
        labels.put("label1", label1);
        labels.put("label2", label2);
        labels.put("label3", label3);
        labels.put("label4", label4);
        labels.put("label5", label5);
        labels.put("label6", label6);
        labels.put("label7", label7);
        labels.put("label8", label8);
        labels.put("label9", label9);
        labels.put("label10", label10);
        labels.put("label11", label11);
        labels.put("label12", label12);
        labels.put("label13", label13);
        labels.put("label14", label14);
        labels.put("label15", label15);
        labels.put("label16", label16);
        labels.put("label17", label17);
        labels.put("label18", label18);
        labels.put("label19", label19);

        indicesV = indicesVacios();
        numerosReales = listaOriginal();
        sc.bingo_soundtrackSound(true);
        iniciar();
    }

    @FXML
    public void accionJugador(){
        comprobarAccion();
    }

    public void cargarDatosJuego(Cita ci){
        citaAtendida = ci;
    }

    @FXML
    public void iniciar(){
        numerosR();
        cambiarNumero();
        casillasVacias();
        accionJugador();
        
    }

    //Genera numeros aleatorios no repetidos
    public static ArrayList<Integer> generarNumeros(){
        ArrayList<Integer> nums = new ArrayList<>();
        while (nums.size() < 20){
            int rd = (int) (Math.random()*80 +1);
            Integer i = (int) rd;
            if(!nums.contains(i)){
                nums.add(i);
            }
        }
        return nums;
    }

    //Comprobar movimiento
    public void comprobarAccion(){
        for(Label l: labels.values()){
            l.setOnMouseClicked(ev -> {
                numAciertos = Integer.parseInt(aciertos.getText());
                numErrores = Integer.parseInt(errores.getText());

                if(l.getText().equals(numero.getText())){
                    numAciertos += 1;
                    this.aciertos.setText(String.valueOf(numAciertos));
                    l.setText("");
                    Image im = new Image(App.class.getResourceAsStream("vista/img/gato/gifgato.gif"), 200.0,100.0, true, false);
                    l.setGraphic(new ImageView(im));
                    sc.bingo_correctSound();
                    cambiarNumero();
                } else {
                    numErrores += 1;
                    this.errores.setText(String.valueOf(numErrores));
                    sc.bingo_errorSound();
                }
            });
        }
    }
    public void numerosR(){
        for(int i = 0; i<20; i++){
            labels.get("label" + String.valueOf(i)).setText(nms.get(i).toString());
        }
    }


    //Cambiar Numero
    public int cambiarNumero(){
        try{
            int numA = (int) (Math.random()*(numerosReales.size()-1));
            this.numero.setText(String.valueOf(numerosReales.get(numA)));
            numerosReales.remove(numA);
            return numA;
        } catch (IndexOutOfBoundsException ex){ //Si ya no quedan números reales el juego acaba y se registra la actividad
            timeline.stop();
            sc.bingo_soundtrackSound(false);
            //Creación de menú dinámico para felicitar al cliente
            anchorPane.getChildren().clear();
            Image im = new Image(App.class.getResourceAsStream("vista/img/gato/congratulations.gif"), 500.0,500.0, true, false);
            ImageView imv = new ImageView(im);
            anchorPane.getChildren().add(imv);
            sc.bingo_congratulationsSound();
            //Se guarda la actividad realizada y se la serializa
            Actividad bingo = new Actividad("Bingo", LocalDate.now(), numAciertos, numErrores,tiempo, citaAtendida);
            ArrayList<Actividad> actividadesAct = ActividadesController.actividadesSer;
            actividadesAct.add(bingo);
            Actividad.actualizarSER(App.pathActividades, actividadesAct);
            //
            //Se muestra por pantalla la alerta para continuar con el proceso
            Alert alertaRegistro = new Alert(Alert.AlertType.INFORMATION);
            alertaRegistro.setTitle("Registro existoso");
            alertaRegistro.setHeaderText("¡Actividad completada, puede consultarla en el menú citas!");
            alertaRegistro.setContentText("Número de aciertos: "+numAciertos+"\nNúmero de errores: "+numErrores+"\nTiempo transcurrido: "+tiempo);
            alertaRegistro.showAndWait();
            RegistrarAtencionController controladorRegistrarA = (RegistrarAtencionController) App.changeRootFXML("vista/fxml/atencion/RegistrarAtencion", RegistrarAtencionController.class);
            controladorRegistrarA.cargarDatosCita(citaAtendida);
        }
        return 0;
    }

    //Cambiar imagen Perro
    public void cambiarImagen(Label l){
        l.setText("");
        Image im = new Image(App.class.getResourceAsStream("vista/img/gato/perro2.jpg"), 200.0,100.0, true, false);
        l.setGraphic(new ImageView(im));
    }

    //Genera indices para las casillas vacias
    public ArrayList<Integer> indicesVacios(){
        ArrayList<Integer> indices = new ArrayList<>();
        while (indices.size() <5){
            int c = (int) (Math.random()*(nms.size()-1));
            Integer n = (int) c;
            if(!indices.contains(n)){
                indices.add(n);
            }
        }
        return indices;
    }

    //cambiar por perritos
    public void casillasVacias(){
        for (Integer i: indicesV){
            labels.get("label" + String.valueOf(i)).setText("");
            cambiarImagen(labels.get("label" + String.valueOf(i)));
        }
    }
       
    public ArrayList<Integer> listaOriginal(){

        ArrayList<Integer> numerosPro = new ArrayList<>();


        Integer n1 = this.nms.get(this.indicesV.get(0));
        Integer n2 = this.nms.get(this.indicesV.get(1));
        Integer n3 = this.nms.get(this.indicesV.get(2));
        Integer n4 = this.nms.get(this.indicesV.get(3));
        Integer n5 = this.nms.get(this.indicesV.get(4));

        for(Integer indiceP: nms){
            if(indiceP != n1 && indiceP != n2 && indiceP != n3 && indiceP != n4 && indiceP != n5){
                numerosPro.add(indiceP);
            }
        }
        return numerosPro;
    }
}