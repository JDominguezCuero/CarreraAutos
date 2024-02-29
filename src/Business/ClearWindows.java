package Business;

import java.util.ArrayList;

public class ClearWindows {
    public static void limpiarAnt(){
        try{
            System.out.println("Presione enter para empezar la carrera...");
            new java.util.Scanner(System.in).nextLine();
            String sistemaOperativo = System.getProperty("os.name");
            ArrayList<String> comando= new ArrayList<>();
            if(sistemaOperativo.contains("Windows")){
                comando.add("cmd");
                comando.add("/C");
                comando.add("cls");

            } else {
                comando.add("clear");
            }

            ProcessBuilder pb = new ProcessBuilder(comando);
            Process iniciarProceso = pb.inheritIO().start();
            iniciarProceso.waitFor();

        }  catch (Exception e) {
            System.out.println("Error al limpiar la pantalla"+e.getMessage());
        }
    }
}
