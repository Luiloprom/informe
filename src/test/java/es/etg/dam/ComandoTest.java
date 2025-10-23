package es.etg.dam;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ComandoTest {

    @Test
    public void testLanzar() throws Exception {
        Proceso p = new Comando("echo hola buenas");
        String resultado = p.lanzar();
        assertEquals("hola buenas", resultado.trim());
    }

    @Test
    public void testEscribir() throws Exception {
        String ruta = "src/main/resources/test_informe.md";
        App.limpiarArchivo(ruta);

        Comando.escribir(ruta, "hola", "echo hola");
        String salida;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            salida = br.readLine();
        }
        assertEquals("## Proceso : echo hola", salida.trim());
    }
}
