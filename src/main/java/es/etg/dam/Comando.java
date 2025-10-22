package es.etg.dam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Comando implements Proceso{
    private String instruccion;

    @Override
    public String lanzar() throws Exception{
        Process p = Runtime.getRuntime().exec(instruccion);
        String linea;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            while ((linea = br.readLine()) != null){
                sb.append(linea).append("\n");
            }
        }
        return sb.toString();
    }

    public static void escribir(String ruta, String contenido) throws Exception{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write(contenido);
        }
    }
}
