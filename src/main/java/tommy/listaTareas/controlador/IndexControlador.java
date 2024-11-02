package tommy.listaTareas.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tommy.listaTareas.modelo.Tarea;
import tommy.listaTareas.servicio.TareaServicio;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexControlador implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class); //para enviar información a consola

    @Autowired
    private TareaServicio tareaServicio;

    @FXML
    private TableView<Tarea> tareaTabla;

    @FXML
    private TableColumn<Tarea,Integer> idTareaColumna;

    @FXML
    private TableColumn<Tarea,String> nombreTareaColumna;

    @FXML
    private TableColumn<Tarea,String> responsableTareaColumna;

    @FXML
    private TableColumn<Tarea,String> estatusTareaColumna;

    //Para mostrar la información de la base de datos
    private final ObservableList<Tarea> tareaLista = FXCollections.observableArrayList(); //cada cambio en la lista se ve de forma automática

    @FXML
    private TextField nombreTareaTexto;
    @FXML
    private TextField responsableTexto;
    @FXML
    private TextField estatusTexto;

    //Método para que se pueda iniciar la app
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //configuro la tabal para que solo se pueda seleccionar un elemento
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        cargarRegistros();
        listarTareas();
    }

    private void cargarRegistros(){
        //Indicamos los atributos que se van a cargar en cada columna
        idTareaColumna.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        nombreTareaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
        responsableTareaColumna.setCellValueFactory(new PropertyValueFactory<>("responsable")); //le paso el mismo nombre que los atributos del objeto Tarea
        estatusTareaColumna.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }

    private void listarTareas(){
        logger.info("Ejecutando lista de tareas...");
        tareaLista.clear();
        tareaLista.addAll(tareaServicio.listarTareas());
        tareaTabla.setItems(tareaLista);
    }

    public void addTarea(){
        if(nombreTareaTexto.getText().isEmpty()){
            mostrarMensaje("Error de validadción","Debes indicar la tarea");
        }else{
            var tarea = new Tarea();
            obtenerDatosFormulario(tarea);
            tareaServicio.guardarTarea(tarea);
            mostrarMensaje("Información","Tarea añadida con éxito");
            limpiarFormulario();
            listarTareas();
        }
    }

    private void mostrarMensaje(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private  void obtenerDatosFormulario(Tarea tarea){
        tarea.setNombreTarea(nombreTareaTexto.getText());
        tarea.setResponsable(responsableTexto.getText());
        tarea.setEstatus(estatusTexto.getText());
    }

    private void limpiarFormulario(){
        nombreTareaTexto.clear();
        responsableTexto.clear();
        estatusTexto.clear();
    }
}
