package cl.duoc.ejercicios_exp2_solucionario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Tarea {
    public String titulo;
    public boolean estaCompletada;
}
