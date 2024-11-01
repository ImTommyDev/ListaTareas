package tommy.listaTareas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import tommy.listaTareas.modelo.Tarea;

//Para hacer consultas a la base de datos, extiende de JpaRepository y se le pasa la entidad y el tipo de la clave primaria
public interface TareaRepositorio extends JpaRepository<Tarea,Integer> {
}
