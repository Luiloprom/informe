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
        assertEquals("## Proceso : echo hola buenas \n hola buenas", resultado);
    }

    @Test
    public void testEscribir() throws Exception {
        Comando.escribir("text.txt", "hola muy buenas");
        try (BufferedReader br = new BufferedReader(new FileReader("text.txt"))) {
            assertEquals("hola muy buenas", br.readLine());
        }
    }
}
