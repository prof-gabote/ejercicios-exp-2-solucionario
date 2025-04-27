package cl.duoc.ejercicios_exp2_solucionario.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cl.duoc.ejercicios_exp2_solucionario.model.Tarea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class TareaRepository {

        private List<Tarea> listaTareas = new ArrayList<>();

        {
                listaTareas = new ArrayList<>(List.of(
                                Tarea.builder().titulo("Hacer la cama").estaCompletada(false).build(),
                                Tarea.builder().titulo("Lavar los platos").estaCompletada(true).build(),
                                Tarea.builder().titulo("Estudiar para el examen").estaCompletada(false).build(),
                                Tarea.builder().titulo("Hacer ejercicio").estaCompletada(true).build(),
                                Tarea.builder().titulo("Leer un libro").estaCompletada(false).build()));

        }

        public List<Tarea> obtenerTodas() {
                return this.listaTareas;
        }

        public Tarea obtenerTareaPorIndice(int indice) {

                return this.listaTareas.get(indice);
        }

        public Tarea obtenerTareaPorTitulo(String tituloTarea) {

                for (Tarea tarea : this.listaTareas) {
                        if (tarea.getTitulo().equalsIgnoreCase(tituloTarea)) {
                                return tarea;
                        }
                }

                return null;
        }

        public void guardarTarea(Tarea tarea) {
                this.listaTareas.add(tarea);
        }

        public void eliminarTarea(String tituloTarea) {
                Tarea tarea = obtenerTareaPorTitulo(tituloTarea);

                this.listaTareas.remove(tarea);
        }

}
