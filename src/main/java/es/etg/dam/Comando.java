package es.etg.dam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Comando implements Proceso{
    private String[] instruccion;

    @Override
    public List<String> lanzar() throws Exception{
        Process p = Runtime.getRuntime().exec(instruccion);
        String linea;
        List<String> contenido = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            while ((linea = br.readLine()) != null){
                contenido.add(linea);
            }
        }
        return contenido;
    }

    @Override
    public void escribir(String ruta, List<String> contenido) throws Exception{
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            for (int i = 0; i < contenido.size(); i++) {
                bw.write(contenido.get(i));
                bw.newLine();
            }
        }
    }
}
