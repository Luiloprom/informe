package es.etg.dam;

import java.util.List;

public class App {

    public static final String[] COMANDO_1 = {"ps"};
    public static final String[] COMANDO_2 = {"df"};
    public static final String[] COMANDO_3 = {"free"};

    public static final String RUTA = "/main/resources/informe.md";

    public static void main(String[] args) throws Exception{
        Proceso p = new Comando(COMANDO_1);
        List<String> salida = p.lanzar();
    }
}