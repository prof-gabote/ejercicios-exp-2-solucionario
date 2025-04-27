package cl.duoc.ejercicios_exp2_solucionario.repository;

import java.util.List;

import cl.duoc.ejercicios_exp2_solucionario.model.Fruta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FrutaRepository {

    private List<Fruta> listaFrutas;

}
