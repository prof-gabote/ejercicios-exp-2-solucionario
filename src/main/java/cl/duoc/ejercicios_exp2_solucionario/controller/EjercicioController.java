package cl.duoc.ejercicios_exp2_solucionario.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ejercicios_exp2_solucionario.model.Fruta;
import cl.duoc.ejercicios_exp2_solucionario.model.Tarea;
import cl.duoc.ejercicios_exp2_solucionario.model.Usuario;
import cl.duoc.ejercicios_exp2_solucionario.repository.FrutaRepository;
import cl.duoc.ejercicios_exp2_solucionario.repository.TareaRepository;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/ejercicios")
public class EjercicioController {

    private static final Logger logger = LoggerFactory.getLogger(EjercicioController.class);
    private final List<Fruta> listaFruta = new ArrayList<>(List.of(
            Fruta.builder().nombre("Manzana").color("Rojo").build(),
            Fruta.builder().nombre("Plátano").color("Amarillo").build(),
            Fruta.builder().nombre("Naranja").color("Naranja").build(),
            Fruta.builder().nombre("Kiwi").color("Verde").build(),
            Fruta.builder().nombre("Uva").color("Morado").build(),
            Fruta.builder().nombre("Fresa").color("Rojo").build(),
            Fruta.builder().nombre("Piña").color("Amarillo").build(),
            Fruta.builder().nombre("Sandía").color("Verde").build(),
            Fruta.builder().nombre("Pera").color("Verde").build()));
    private final FrutaRepository frutaRepository = new FrutaRepository();

    private final List<Tarea> listaTareas = new ArrayList<>(List.of(
            Tarea.builder().titulo("Hacer la cama").estaCompletada(false).build(),
            Tarea.builder().titulo("Lavar los platos").estaCompletada(true).build(),
            Tarea.builder().titulo("Estudiar para el examen").estaCompletada(false).build(),
            Tarea.builder().titulo("Hacer ejercicio").estaCompletada(true).build(),
            Tarea.builder().titulo("Leer un libro").estaCompletada(false).build()));
    private final TareaRepository tareaRepository = new TareaRepository();

    /* EJERCICIO 1 */
    @GetMapping("/casoUso1")
    public String obtenerEstado() {

        logger.info("Se esta consultando el endpoint /casoUso1");

        String mensaje = "El ms está activo 👍.";

        return mensaje;
    }

    /* EJERCICIO 2 */

    /*
     * SOLUCIÓN 1
     * 
     * @PostMapping("/casoUso2")
     * public String devolverDatosUsuario (@RequestBody String dataDeLaSolicitud) {
     * 
     * logger.info("El dato que llego de la soli fue " + dataDeLaSolicitud);
     * 
     * String dataQueDevolvere = dataDeLaSolicitud + " - data llego OK";
     * 
     * logger.info(dataQueDevolvere);
     * 
     * return dataQueDevolvere;
     * }
     */

    /* SOLUCIÓN 2 */
    @PostMapping("/casoUso2")
    public Usuario devolverDatosUsuario(@RequestBody Usuario data) {

        logger.info("Llegó esta data" + data);

        Usuario usuarioADevolver = Usuario.builder()
                .nombre(data.getNombre())
                .correo(data.getCorreo())
                .build();

        logger.info("" + usuarioADevolver);

        return usuarioADevolver;

    }

    /* EJERCICIO 3 */

    /*
     * SOLUCIÓN 1
     * 
     * @GetMapping("/casoUso3/{a}/{b}")
     * public int obtenerSuma(@PathVariable int a, @PathVariable int b) {
     * 
     * int resultado = a + b;
     * 
     * return resultado;
     * }
     */

    @GetMapping("/casoUso3/")
    public int obtenerSuma(@RequestParam int a, @RequestParam int b) {

        int resultado = a + b;

        return resultado;
    }

    /* EJERCICIO 4 */

    /*
     * SOLUCIÓN 1
     * 
     * @GetMapping("/casoUso4")
     * public List<Fruta> obtenerFrutas() {
     * 
     * List<Fruta> stockActual = frutaRepository.getListaFrutas();
     * 
     * return stockActual;
     * }
     */

    @GetMapping("/casoUso4")
    public List<Fruta> obtenerFrutas() {

        frutaRepository.setListaFrutas(listaFruta);

        List<Fruta> stockActual = frutaRepository.getListaFrutas();

        return stockActual;
    }

    /* EJERCICIO 5 */

    /*
     * SOLUCIÓN 1
     * 
     * @GetMapping("/casoUso5")
     * public String obtenerEdad(@RequestParam int data) {
     * 
     * int anioActual = 2025;
     * int anioNac = data;
     * 
     * int edad = anioActual - anioNac;
     * 
     * return "La edad del cliente es " + edad;
     * }
     */

    /*
     * SOLUCIÓN 2
     * 
     * @GetMapping("/casoUso5")
     * public String obtenerEdad(@RequestParam int anioNac) {
     * 
     * int anioActual = 2025;
     * int edad = anioActual - anioNac;
     * 
     * return "La edad del cliente es " + edad;
     * 
     * }
     */

    @GetMapping("/casoUso5")
    public String obtenerEdad(@RequestParam LocalDate fechaNac) {

        LocalDate hoy = LocalDate.now();

        logger.info("La fecha de hoy es " + hoy);
        logger.info("La fecha de nacimiento es " + fechaNac);

        int edad = Period.between(fechaNac, hoy).getYears();

        logger.info("Periodo: " + edad);

        return "La edad del cliente es " + edad;

    }

    /* EJERCICIO 6 */

    /*
     * SOLUCIÓN 1
     * 
     * @GetMapping("/casoUso6")
     * public List<Fruta> obtenerFrutasPorColor(@RequestParam String color) {
     * frutaRepository.setListaFrutas(listaFruta);
     * List<Fruta> stockFruta = frutaRepository.getListaFrutas();
     * List<Fruta> listaRetorno = new ArrayList<>();
     * 
     * for (Fruta fruta : stockFruta) {
     * String nombreFruta = fruta.getNombre();
     * String colorFruta = fruta.getColor();
     * 
     * if (colorFruta.equalsIgnoreCase(color)) {
     * listaRetorno.add(fruta);
     * logger.info("La fruta " + nombreFruta + " es de color " + color +
     * " Y se agrega a la lisa.");
     * } else {
     * logger.info("La fruta " + nombreFruta + " NO se agrega a la lista");
     * }
     * }
     * 
     * logger.info("Lista " + listaRetorno);
     * 
     * if (listaRetorno.isEmpty()) {
     * listaRetorno.add(new Fruta());
     * return listaRetorno;
     * }
     * 
     * return listaRetorno;
     * }
     */

     /* SOLUCIÓN 2 */
    @GetMapping("/casoUso6")
    public List<Fruta> obtenerFrutasPorColor(@RequestParam String color) {
        frutaRepository.setListaFrutas(listaFruta);
        List<Fruta> stockFruta = frutaRepository.getListaFrutas();

        List<Fruta> listaRetorno = stockFruta.stream()
        .filter(
            fruta -> fruta.getColor().equalsIgnoreCase(color)
        )
        .toList();

        return listaRetorno;
    }

    /* EJERCICIO 7 */
    /* SOLUCIÓN 1 
    @PostMapping("/casoUso7")
    public String verificarTarea(@RequestBody Tarea tareaRequest) {
        tareaRepository.setListaTareas(listaTareas);
        List<Tarea> stockTareas = tareaRepository.getListaTareas();
        boolean existe = false;

        for (Tarea tarea : stockTareas) {
            String tituloTarea = tarea.getTitulo();
            String tituloTareaRequest = tareaRequest.getTitulo();

            logger.info(tituloTarea);

            if (tituloTarea.equalsIgnoreCase(tituloTareaRequest)) {
                existe = true;
                break;
            } else {
                existe = false;
            }
        }

        return existe ? "La tarea ya existe" : "La tarea no existe";
    }
    */

    @PostMapping("/casoUso7")
    public String verificarTarea(@RequestBody Tarea tareaRequest) {
        tareaRepository.setListaTareas(listaTareas);
        List<Tarea> stockTareas = tareaRepository.getListaTareas();

        boolean existe = stockTareas.stream()
        .anyMatch(
            tarea -> tarea.getTitulo().equalsIgnoreCase(tareaRequest.getTitulo())
        );

        return existe ? "La tarea ya existe" : "La tarea no existe";
    }

    /* EJERCICIO 8 */
    /* SOLUCIÓN 1 
    @GetMapping("/casoUso8")
    public String resumenTareas() {
        int total = 0;

        tareaRepository.setListaTareas(listaTareas);
        List<Tarea> stockTareas = tareaRepository.getListaTareas();

        for (Tarea tarea : stockTareas) {
            total++;
        }

        return "El total de tareas registradas es de " + total;
    }
    */

    @GetMapping("/casoUso8")
    public String resumenTareas() {
        tareaRepository.setListaTareas(listaTareas);
        List<Tarea> stockTareas = tareaRepository.getListaTareas();

        int total = stockTareas.size();

        return "El total de tareas registradas es de " + total;
    }

    /* EJERCICIO 9 */

    /* SOLUCIÓN 1 
    @GetMapping("/casoUso9")
    public String obtenerTareasIncompletas() {
        tareaRepository.setListaTareas(listaTareas);
        List<Tarea> stockTareas = tareaRepository.getListaTareas();

        List<Tarea> listaRetorno = new ArrayList<>();

        for (Tarea tarea : stockTareas) {
            if (tarea.estaCompletada == false) {

                listaRetorno.add(tarea);
                logger.info("Tarea " + tarea + " agregadada.");
                
            }
        }

        int total = listaRetorno.size();

        return "El total de tareas incompletas es de " + total;
    }
    */
    /* SOLUCIÓN 2 */
    @GetMapping("/casoUso9")
    public String obtenerTareasIncompletas() {
        tareaRepository.setListaTareas(listaTareas);
        List<Tarea> stockTareas = tareaRepository.getListaTareas();

        int total = (int) stockTareas.stream()
        .filter(tarea -> !tarea.isEstaCompletada()).count();

        return "El total de tareas incompletas es de " + total;
    }

}
