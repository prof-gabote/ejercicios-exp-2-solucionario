package cl.duoc.ejercicios_exp2_solucionario;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.ejercicios_exp2_solucionario.model.Tarea;
import cl.duoc.ejercicios_exp2_solucionario.repository.TareaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class TareaService {

    private TareaRepository tareaRepo;

    public List<Tarea> obtenerTodasLasTareas(){
        return tareaRepo.obtenerTodas();
    }

    public Tarea obtenerTareaPorIndice(int indice){
        return tareaRepo.obtenerTareaPorIndice(indice);
    }

    public Tarea obtenerTareaPorTitulo(String tituloTarea){
        return tareaRepo.obtenerTareaPorTitulo(tituloTarea);
    }

    public void guardarTarea(Tarea tarea){
        tareaRepo.guardarTarea(tarea);
    }

    public void eliminarTareaPorTitulo(String tituloTarea){
        tareaRepo.eliminarTarea(tituloTarea);
    }


}
