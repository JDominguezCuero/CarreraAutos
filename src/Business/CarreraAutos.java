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
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String reset = "\u001B[0m";
    public static List<Auto> crearAutos() {
        List<Auto> autos = new ArrayList<>();
        int numAutos = Integer.parseInt(valT.pedirEntradaNumeros(scanner));
        String C_Azul = "\uD83D\uDE98";
        String C_Amarillo = "\uD83D\uDE96";

        for (int i = 0; i < numAutos; i++) {
            String nombre = valT.pedirEntradaTexto(scanner, i);
            String color = (i == 0) ? ANSI_BLUE : ANSI_YELLOW;
            String tipoCarro = (i == 0) ? C_Azul : C_Amarillo;
            autos.add(new Auto(nombre, color, tipoCarro));
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
                carreraFinalizada = avanzarAuto(auto, pista);
                if (carreraFinalizada) {
                    break;
                }
            }
            imprimirPista(autos, pista);
            pausaCarrera();
        }
    }

    public static boolean avanzarAuto(Auto auto, String[] pista) {
        boolean carreraFinalizada = false;

        int avance = rand.nextInt(3) + 1;
        auto.posicion += avance;
        auto.avance = avance;

        if (auto.posicion >= pista.length) {
            auto.posicion = pista.length - 1;
            return true;
        }

        if (pista[auto.posicion].equals("&")) {
            String choque = "\uD83D\uDCA5";
            System.out.print(choque.toCharArray());
            System.out.print(reset + "¡Choque para el auto " + auto.nombre + "!");
            System.out.println(choque.toCharArray());
            auto.posicion -= avance;
        }
        System.out.println(auto.color + auto.nombre + " se ha desplazado en " + auto.posicion + " movimientos, y" +
                " avanzará " + auto.avance + " posiciones." + reset);

        return false;
    }

    public static void imprimirPista(List<Auto> autos, String[] pista) {
        for (int i = 0; i < pista.length; i++) {
            if (pista[i].equals("&")) {
                String obst = "\uD83C\uDF32";
                System.out.print(obst.toCharArray());
            } else {
                boolean autoPresente = false;
                for (Auto auto : autos) {
                    if (auto.posicion == i) {
                        System.out.print(auto.tipoCoche.toCharArray());
                        autoPresente = true;
                        break;
                    }
                }
                if (!autoPresente) {
                    System.out.print("-" + reset);
                }
            }
        }
        System.out.println("🏁");
        System.out.println();
    }

    public static void pausaCarrera() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
