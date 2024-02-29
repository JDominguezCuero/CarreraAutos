
import Integrations.Auto;
import java.util.List;

import static Business.CarreraAutos.*;

public class Main {

    public static void main(String[] args) {

        System.out.println("¡Bienvenido a la Carrera de Autos!");

        List<Auto> autos = crearAutos();
        String[] pista = prepararPista();
        colocarArbolesEnPista(pista);
        carrera(autos, pista);
        mostrarGanador(autos);

        //Limpiamos consola
        //limpiarAnt();

    }

}