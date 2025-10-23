# EJERCICIO PROCESOS – PS, DF y FREE  

Repositorio: [https://github.com/Luiloprom/informe.git](https://github.com/Luiloprom/informe.git)


# Índice

- [EJERCICIO PROCESOS – PS, DF y FREE](#ejercicio-procesos-–-ps-df-y-free)
- [Repositorio](#repositorio)
- [Main](#main)
- [Comando.java](#comandojava)
- [Test con JUnit](#test-con-junit)
  - [testLanzar](#testlanzar)
  - [testEscribir](#testescribir)

---

### Main

En mi archivo **App.java** hago un for each para cada comando y los lanzo, luego con su salida llamo al metodo escribir para que escriba en el archivo

```java
    public static void main(String[] args) throws Exception {
        limpiarArchivo(RUTA);

        for (String comando : COMANDOS) {
            Proceso exec = new Comando(comando);
            String salida = exec.lanzar();
            Comando.escribir(RUTA, salida, comando);
        }

        System.out.println(RESPUESTA);
    }
```
---

#### Comando.java 

En mi archivo **Comando.java** implemento la interfaz `Proceso` que tiene un metodo `lanzar` con el que lanzo los comandos y me devuelve su salida en un String.

```java
    @Override
    public String lanzar() throws Exception {
        Process p = Runtime.getRuntime().exec(instruccion);
        String linea;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append("\n");
            }
        }
        return (p.waitFor() == 0) ? sb.toString() : MSG_ERROR;
    }
```

---

Luego tengo el metodo `escribir` que escribe en el archivo markdown
```java
    public static void escribir(String ruta, String contenido, String comando) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(MSG_TITULO + comando + "\n");
            bw.write(contenido + "\n");
        }
    }
```
---

### Test con JUnit  

Estos test verifican que los metodos `lanzar` y `escribir` funcionan correctamente.

- Test **testLanzar**
```java
    @Test
    public void testLanzar() throws Exception {
        Proceso p = new Comando("echo hola buenas");
        String resultado = p.lanzar();
        assertEquals("hola buenas", resultado.trim());
    }
```
> Uso `trim` porque `echo` mete siempre un `\n` automatico.

---

- Test **testEscribir**
```java
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
```