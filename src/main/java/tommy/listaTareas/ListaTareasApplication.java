package tommy.listaTareas;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tommy.listaTareas.presentacion.ListaTareasFx;

@SpringBootApplication
public class ListaTareasApplication {


	public static void main(String[] args) {
		//SpringApplication.run(ListaTareasApplication.class, args);
		Application.launch(ListaTareasFx.class, args); //Para ejecutar la aplicación de spring y fx
	}

}
