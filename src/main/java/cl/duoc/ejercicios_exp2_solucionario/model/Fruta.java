package cl.duoc.ejercicios_exp2_solucionario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Fruta {

    private String nombre;
    private String color;

}
