package g05.controlador.cliente;
import g05.App;
import g05.controlador.SoundController;
import g05.controlador.cliente.ClientesController;
import g05.modelo.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controlado asociado a la agregacion de clientes
 * Autor: Grupo 5
 * Version:1.0
 */
public class AgregarClienteController implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button botonAgregarC;

    @FXML
    private Button botonCancelarC;

    @FXML
    private TextField txtCedC;

    @FXML
    private TextField txtCorreoC;

    @FXML
    private TextField txtDatR;

    @FXML
    private TextField txtNomC;

    @FXML
    private TextField txtTelC;

    String nombre;
    String cedula;
    String email;
    String telefono;
    String datosRepresentante;

    /**
     * Incializa apenas se ejecuta el programa
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SoundController sc = new SoundController();
        botonAgregarC.setDisable(true);
        botonAgregarC.setOnMouseEntered(mouseEvent -> sc.button_hoverSound());
        botonCancelarC.setOnMouseEntered(mouseEvent -> sc.button_hoverSound());
    }

    /**
     * Agrega el cliente al regsitro
     * @param event
     */
    @FXML
    public void agregarCliente(ActionEvent event) {
        nombre = txtNomC.getText();
        cedula = txtCedC.getText();
        email = txtCorreoC.getText().toLowerCase();
        telefono = txtTelC.getText();
        datosRepresentante = txtTelC.getText();
        Cliente cliente = new Cliente(nombre, cedula, email, telefono, datosRepresentante);
        escribirCliente(cliente);
        Alert alertaRegistro = new Alert(Alert.AlertType.INFORMATION);
        alertaRegistro.setTitle("Registro existoso");
        alertaRegistro.setHeaderText("REGISTRO");
        alertaRegistro.setContentText("Cliente "+nombre+" registrado correctamente!");
        alertaRegistro.showAndWait();
        App.changeRootFXML("vista/fxml/cliente/Clientes");
    }

    /**
     * Regresa a la ventana principal de clientes
     * @param event
     */
    @FXML
    void backAgregarCliente(ActionEvent event){
        App.changeRootFXML("vista/fxml/cliente/Clientes");
    }

    /**
     * Verifica que los datos sean los correctos
     * @param event
     */
    @FXML
    public void datosCorrectos(KeyEvent event){
        if(txtNomC.getText()!="" && txtCorreoC.getText()!="" && txtCedC.getText()!= "" && txtTelC.getText()!="" && txtDatR.getText()!=""){
            botonAgregarC.setDisable(false);
        } else {
            botonAgregarC.setDisable(true);
        }
    }

    /**
     * Escribe los datos del cliente en el arxhivo
     * @param c
     */
    void escribirCliente(Cliente c){
        ArrayList<Cliente> clientes = ClientesController.clientesCSV;
        clientes.add(c);
        Cliente.actualizarCSV(App.pathClientesCSV,clientes);
    }
}