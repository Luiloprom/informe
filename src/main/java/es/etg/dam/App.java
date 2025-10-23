package es.etg.dam;

import java.io.FileWriter;

public class App {

    public static final String COMANDO_1 = "ps";
    public static final String COMANDO_2 = "df";
    public static final String COMANDO_3 = "free";

    public static final String[] COMANDOS = { COMANDO_1, COMANDO_2, COMANDO_3 };

    public static final String RUTA = "src/main/resources/informe.md";

    public static final String RESPUESTA = "Resultado escrito correctamente";

    public static void main(String[] args) throws Exception {
        limpiarArchivo(RUTA);

        for (String comando : COMANDOS) {
            Proceso exec = new Comando(comando);
            String salida = exec.lanzar();
            Comando.escribir(RUTA, salida, comando);
        }

        System.out.println(RESPUESTA);
    }

    public static void limpiarArchivo(String ruta) throws Exception {
        new FileWriter(ruta, false).close();
    }
}