package tommy.listaTareas.servicio;

import tommy.listaTareas.modelo.Tarea;

import java.util.List;

public interface InterfazTareaServicio {
    //Añado los métodos para recuperar la información y poder interactuar con el repositorio y con la base de datos
    public List<Tarea> listarTareas();

    public Tarea buscarTareaPorId(Integer idTarea);

    //Método para guardar o modificar una tarea, si el id es != nul, modificará, si es null la creará (lo hace hibernate automático)
    public void guardarTarea(Tarea tarea);

    public void eliminarTarea(Tarea tarea);
}
