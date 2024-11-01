package tommy.listaTareas.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tommy.listaTareas.modelo.Tarea;
import tommy.listaTareas.repositorio.TareaRepositorio;

import java.util.List;

@Service //para que sea un componente de spring
public class TareaServicio implements InterfazTareaServicio{

    //Atributo para poder interactuar con el repositorio
    @Autowired //Lo inyectamos de manera automática
    private TareaRepositorio tareaRepositorio;

    @Override
    public List<Tarea> listarTareas() {
        return tareaRepositorio.findAll();
    }

    @Override
    public Tarea buscarTareaPorId(Integer idTarea) {
        Tarea tarea = tareaRepositorio.findById(idTarea).orElse(null);
        return tarea;
    }

    @Override
    public void guardarTarea(Tarea tarea) {
        tareaRepositorio.save(tarea);
    }

    @Override
    public void eliminarTarea(Tarea tarea) {
        tareaRepositorio.delete(tarea);
    }
}
