package es.etg.dam;

public class App {

    public static final String COMANDO_1 = "ps";
    public static final String COMANDO_2 = "df";
    public static final String COMANDO_3 = "free";

    public static final String[] COMANDOS = { COMANDO_1, COMANDO_2, COMANDO_3 };

    public static final String RUTA = "src/main/resources/informe.md";

    public static final String RESPUESTA = "Resultado escrito correctamente";

    public static void main(String[] args) throws Exception {
        StringBuilder salida = new StringBuilder();
        for (String comando : COMANDOS) {
            Proceso exec = new Comando(comando);
            salida.append(exec.lanzar());
        }

        Comando.escribir(RUTA, salida.toString());
        System.out.println(RESPUESTA);
    }
}