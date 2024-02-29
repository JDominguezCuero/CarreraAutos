package Integrations;

public class Auto
{
    public String nombre;
    public int posicion;
    public int avance;
    public String color;
    public String tipoCoche;

    public Auto(String nombre, String color, String tipoCoche) {
        this.nombre =  nombre;
        this.posicion = 0;
        this.color = color;
        this.avance = 0;
        this.tipoCoche = tipoCoche;
    }
}
