package cl.duoc.ejercicios_exp2_solucionario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ejercicios_exp2_solucionario.TareaService;
import cl.duoc.ejercicios_exp2_solucionario.model.Tarea;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpHeaders;



@RestController
@RequestMapping("/tarea")
@RequiredArgsConstructor
public class TareaController {

    private final TareaService tareaService;
    private static final Logger logger = LoggerFactory.getLogger(TareaController.class);

    @GetMapping("/estado")
    public ResponseEntity<String> obtenerEstado() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Endpoint", "/tarea/estado");
        headers.set("Descripcion", "Verifica el estado del servicio");
        headers.set("Estado", "OK");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body("El servicio está funcionando correctamente");

    }

    @GetMapping("/listar")
    public ResponseEntity<List<Tarea>> listarTareas() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Endpoint", "/tarea/listar");
        headers.set("Descripcion", "Lista todas las tareas");
        headers.set("Estado", "Acepted");

        logger.info("Listando todas las tareas");
        List<Tarea> tareas = tareaService.obtenerTodasLasTareas();
        return ResponseEntity.accepted().headers(headers).body(tareas); 
    }

    @GetMapping("/listar/{indice}")
    public ResponseEntity<Tarea> obtenerTareaPorIndice(@PathVariable int indice) {

        logger.info("Obteniendo tarea por índice: " + indice);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Endpoint", "/tarea/listar/{indice}");
        headers.set("Descripcion", "Obtiene una tarea por su índice");

        Tarea tarea = tareaService.obtenerTareaPorIndice(indice);
        if (tarea != null) {
            return ResponseEntity.ok().headers(headers).body(tarea);
        } else {
            headers.set("Estado", "No Encontrado");
            return ResponseEntity.notFound().headers(headers).build();
        }
    }

    @GetMapping("/listar/titulo/")
    public ResponseEntity<Tarea> obtenerTareaPorTitulo(@RequestParam String tituloTarea) {

        logger.info("Obteniendo tarea por título: " + tituloTarea);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Endpoint", "/tarea/listar/titulo/");
        headers.set("Descripcion", "Obtiene una tarea por su título");

        Tarea tarea = tareaService.obtenerTareaPorTitulo(tituloTarea);
        if (tarea != null) {
            return ResponseEntity.ok().headers(headers).body(tarea);
        } else {
            headers.set("Estado", "No Encontrado");
            return ResponseEntity.notFound().headers(headers).build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> guardarTarea(@RequestBody Tarea tarea) {

        logger.info("Guardando tarea: " + tarea.getTitulo());
        tareaService.guardarTarea(tarea);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Endpoint", "/tarea/guardar");
        headers.set("Descripcion", "Guarda una nueva tarea");
        headers.set("Estado", "Creado");

        return ResponseEntity.status(201).headers(headers).body("Tarea guardada correctamente");
    }

    @DeleteMapping("/eliminar/")
    public ResponseEntity<String> eliminarTarea(@RequestParam String tituloTarea) {

        logger.info("Eliminando tarea: " + tituloTarea);
        tareaService.eliminarTareaPorTitulo(tituloTarea);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Endpoint", "/tarea/eliminar/");
        headers.set("Descripcion", "Elimina una tarea por su título");
        headers.set("Estado", "No Contenido");

        return ResponseEntity.noContent().headers(headers).build();
    }
}
