package es.etg.dam;

import java.util.List;

public interface Proceso {
    List<String>lanzar() throws Exception;
    void escribir(String ruta, List<String> contenido) throws Exception;
}
