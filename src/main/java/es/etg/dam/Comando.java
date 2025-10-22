package es.etg.dam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comando implements Proceso {
    public final String MSG_ERROR = "A ocurrido un error";
    public final String MSG_TITULO = "## Proceso : ";

    private String instruccion;

    @Override
    public String lanzar() throws Exception {
        Process p = Runtime.getRuntime().exec(instruccion);
        String linea;
        StringBuilder sb = new StringBuilder();
        sb.append(MSG_TITULO).append(instruccion).append("\n");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        }

        return (p.waitFor() == 0) ? sb.toString() : MSG_ERROR;
    }

    public static void escribir(String ruta, String contenido) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write(contenido);
        }
    }
}
