package es.etg.dam;

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
        String ruta = "src/test/resources/test_informe.md";
        Comando.escribir(ruta, "hola", "test");

    }
}
