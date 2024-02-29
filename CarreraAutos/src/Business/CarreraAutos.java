package Business;

import Integrations.Auto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CarreraAutos {
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();
    static ValidacionTipo valT = new ValidacionTipo();
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String reset = "\u001B[0m";
    public static List<Auto> crearAutos() {
        List<Auto> autos = new ArrayList<>();
        int numAutos = Integer.parseInt(valT.pedirEntradaNumeros(scanner));

        for (int i = 0; i < numAutos; i++) {
            String nombre = valT.pedirEntradaTexto(scanner, i);
            String color = (i == 0) ? ANSI_RED : ANSI_BLUE;
            autos.add(new Auto(nombre, color));
        }
        return autos;
    }

    public static String[] prepararPista() {
        int longitudPista = Integer.parseInt(valT.pedirEntradaTamaño(scanner));
        scanner.nextLine(); // Consumir la nueva línea pendiente

        String[] pista = new String[longitudPista];
        for (int i = 0; i < pista.length; i++) {
            pista[i] = "-";
        }
        return pista;
    }

    public static void colocarArbolesEnPista(String[] pista) {
        int numArboles = rand.nextInt(3) + 1;
        for (int i = 0; i < numArboles; i++) {
            int posicionArbol = rand.nextInt(pista.length);
            pista[posicionArbol] = "&";
        }
    }

    public static void carrera(List<Auto> autos, String[] pista) {
        boolean carreraFinalizada = false;
        while (!carreraFinalizada) {
            for (Auto auto : autos) {
                avanzarAuto(auto, pista);
            }
            imprimirPista(autos, pista);
            pausaCarrera();
        }
    }

    public static void avanzarAuto(Auto auto, String[] pista) {
        boolean carreraFinalizada = false;

        int avance = rand.nextInt(3) + 1;
        auto.posicion += avance;
        auto.avance = avance;

        if (auto.posicion >= pista.length) {
            auto.posicion = pista.length - 1;
            carreraFinalizada = true;
        }

        if (pista[auto.posicion].equals("&")) {
            System.out.println(reset + "¡Choque para el auto " + auto.nombre + "!");
            auto.posicion -= avance;
        }
        System.out.println(auto.color + auto.nombre + " se ha desplazado en " + auto.posicion + " movimientos, y" +
                " avanzará " + auto.avance + " posiciones." + reset);
    }

    public static void imprimirPista(List<Auto> autos, String[] pista) {
        for (int i = 0; i < pista.length; i++) {
            if (pista[i].equals("&")) {
                System.out.print("&");
            } else {
                boolean autoPresente = false;
                for (Auto auto : autos) {
                    if (auto.posicion == i) {
                        System.out.print(auto.color + auto.nombre.charAt(0) + reset);
                        autoPresente = true;
                        break;
                    }
                }
                if (!autoPresente) {
                    System.out.print("-" + reset);
                }
            }
        }
        System.out.println();
    }

    public static void pausaCarrera() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        limpiarPantalla();
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mostrarGanador(List<Auto> autos) {
        Auto ganador = autos.get(0);
        for (Auto auto : autos) {
            if (auto.posicion > ganador.posicion) {
                ganador = auto;
            }
        }
        System.out.println(ganador.color + "¡La carrera ha finalizado! El ganador es el auto " + ganador.nombre + ".");
    }
}
