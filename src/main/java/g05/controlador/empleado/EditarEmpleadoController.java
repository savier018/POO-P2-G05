package g05.controlador.empleado;
import g05.App;
import g05.controlador.SoundController;
import g05.modelo.Empleado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controlador asociado a la ventana de editar empleado
 * Autor: Grupo 5
 * Version: 1.0
 */
public class EditarEmpleadoController implements Initializable {
    @FXML
    private RadioButton rbA;
    @FXML
    private RadioButton rbI;
    @FXML
    private Button botonEditarE;
    @FXML
    private Button botonCancelarE;
    @FXML
    private ToggleGroup estado;
    @FXML
    private Label lbCed;
    @FXML
    private TextField txtCorreoE;
    @FXML
    private TextField txtNomE;
    @FXML
    private TextField txtTelE;
    private Empleado empleadoSeleccionado;

    /**
     * Inicializa apenas se ejecute el programa
     * Autor: Grupo 5
     * Version: 1.0
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SoundController sc = new SoundController();
        botonEditarE.setOnMouseEntered(ev -> sc.button_hoverSound());
        botonCancelarE.setOnMouseEntered(ev -> sc.button_hoverSound());
    }

    /**
     * Edita el empelado segun los datos ingresados por el usuario
     * @param event
     */
    @FXML
    void editarEmpleado(ActionEvent event) {

        RadioButton selectedRadioButton = (RadioButton) estado.getSelectedToggle();
        if(!selectedRadioButton.getText().equals("Activo")){
            empleadoSeleccionado.setEstado(false);
        } else{
            empleadoSeleccionado.setEstado(true);
        }
        empleadoSeleccionado.setNombre(txtNomE.getText());
        empleadoSeleccionado.setTelefono(txtTelE.getText());
        empleadoSeleccionado.setEmail(txtCorreoE.getText());

        ArrayList<Empleado> empleados = EmpleadosController.empleadosCSV;
        if(empleados.contains(empleadoSeleccionado)){
            int ind = empleados.indexOf(empleadoSeleccionado);
            empleados.set(ind, empleadoSeleccionado);
            Empleado.actualizarCSV(App.pathEmpleadosCSV,empleados);
            App.changeRootFXML("vista/fxml/empleado/Empleados");
        }
    }

    /**
     * Regresa a la ventana principal de empleado
     * @param event
     */
    @FXML
    void backEditarEmpleados(ActionEvent event) {
        App.changeRootFXML("vista/fxml/empleado/Empleados");
    }

    /**
     * Verifica que los datos ingresados por el usuario esten correctos
     * @param event
     */
    @FXML
    void datosCorrectos(KeyEvent event) {
        if(txtNomE.getText()!="" && txtCorreoE.getText()!="" && txtTelE.getText()!= ""){
            botonEditarE.setDisable(false);
        } else {
            botonEditarE.setDisable(true);
        }
    }

    /**
     * Carga los datos del empleado sleccionado en la ventana de editar
     * @param e
     */
    public void cargarDatosEmpleado(Empleado e){
        lbCed.setText(e.getCedula());
        txtNomE.setText(e.getNombre());
        txtTelE.setText(e.getTelefono());
        txtCorreoE.setText(e.getEmail());
        if (e.getEstado().equals("Activo"))
            rbA.setSelected(true);
        else
            rbI.setSelected(true);
        empleadoSeleccionado = e;
    }
}