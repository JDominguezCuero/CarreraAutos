package Business;

import java.util.Scanner;

public class ValidacionTipo {
    // Método main para probar los métodos
    public static void main(String[] args) {
        ValidacionTipo validador = new ValidacionTipo();
        Scanner scanner = new Scanner(System.in);

        // Pedir entrada de texto
        /*String entradaTexto = validador.pedirEntradaTexto(scanner);
        System.out.println("Texto introducido: " + entradaTexto);


        // Pedir entrada de números
        String entradaNumeros = validador.pedirEntradaNumeros(scanner);
        System.out.println("Número introducido: " + entradaNumeros);*/

        scanner.close();
    }

    // Método para validar si una cadena contiene solo texto
    public boolean esSoloTexto(String texto) {
        return texto.matches("[a-zA-Z]+");
    }

    // Método para validar si una cadena contiene solo números
    public boolean esSoloNumeros(String texto) {
        return texto.matches("^(?:[0-9]|[1-2][0-9]|30)$");
    }

    // Método para pedir una entrada de texto y manejar excepciones
    public String pedirEntradaTexto(Scanner scanner, int i) {
        String entrada;
        do {
            System.out.print("Ingrese el nombre del auto " + (i + 1) + ": ");
            entrada = scanner.nextLine();
            try {
                if (!esSoloTexto(entrada)) {
                    throw new IllegalArgumentException("La entrada contiene caracteres no válidos. " +
                            "Solo se permiten letras.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!esSoloTexto(entrada));
        return entrada;
    }

    // Método para pedir una entrada de números y manejar excepciones
    public String pedirEntradaNumeros(Scanner scanner) {
        String entrada;
        do {
            System.out.print("Ingrese el número de autos: ");
            entrada = scanner.nextLine();
            try {
                if (!esSoloNumeros(entrada)) {
                    throw new IllegalArgumentException("La entrada contiene caracteres no válidos. " +
                            "Solo se permiten números enteros.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!esSoloNumeros(entrada));
        return entrada;
    }

    public String pedirEntradaTamaño(Scanner scanner) {
        String entrada;
        do {
            System.out.print("Ingrese el tamaño de la pista: ");
            entrada = scanner.nextLine();
            try {
                if (!esSoloNumeros(entrada)) {
                    throw new IllegalArgumentException("La entrada contiene caracteres no válidos. " +
                            "Solo se permiten números enteros del 1 al 30.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!esSoloNumeros(entrada));

        System.out.println("Enter para continuar");
        return entrada;
    }

}

