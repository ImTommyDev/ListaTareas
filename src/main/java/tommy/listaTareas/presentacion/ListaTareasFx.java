package tommy.listaTareas.presentacion;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import tommy.listaTareas.ListaTareasApplication;


public class ListaTareasFx extends Application {


    private ConfigurableApplicationContext applicationContext;

    @Override
    public  void init(){
        this.applicationContext = new SpringApplicationBuilder(ListaTareasApplication.class).run(); //le pasamos la clase de Spring que vamos a utilizar
        //Run para que se ejecute
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(ListaTareasApplication.class.getResource("/templates/index.fxml"));
        loader.setControllerFactory(applicationContext::getBean); //Obtenemos todos los objetos de spring y los cargamos en java fx
        Scene escena = new Scene(loader.load());
        stage.setScene(escena);
        stage.show();
    }

    @Override
    public void stop(){
        applicationContext.close(); //Cerramos la fábrica de spring
        Platform.exit(); //Cerramos la app de java fx
    }
}
