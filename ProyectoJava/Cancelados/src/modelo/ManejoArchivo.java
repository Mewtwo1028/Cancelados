package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class ManejoArchivo {

    private final Path path = Paths.get("");
    private final String rutaActual = path.toAbsolutePath().toString();
    private final String nombreArchivo = "configuracion.cconfig";
    private final String rutaArchivo = rutaActual + "/src/configuraciones/" + nombreArchivo;

    public ManejoArchivo() {
        if (!existeArchivo()) {
            crearArchivo();
            initArchivo();
        }
    }

    private void initArchivo() {
        HashMap<String, String> hm = new HashMap();
        hm.put("nombreusuario", "defecto");
        hm.put("idempleado", "1");
        escribirArchivo(hm);
    }

    /**
     * Verifica si el archivo especificado en la ruta por defecto existe.
     *
     * @return true si el archivo existe, false en caso contrario.
     */
    private boolean existeArchivo() {
        return new File(rutaArchivo).exists();
    }

    /**
     * Crea un archivo en la ruta por defecto.
     *
     * @return true si el archivo se crea correctamente, false en caso
     * contrario.
     */
    private boolean crearArchivo() {
        try (FileWriter archivo = new FileWriter(rutaArchivo)) {
            return true;
        } catch (IOException ex) {
            System.out.println("Error al crearArchivo: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Verifica si existe una clave dentro de un HashMap
     *
     * @param clave la clave a buscar
     * @param hashMapRecuperado el HashMap donde realizara la busqueda
     * @return true si existe la clave, false en cualquier otro caso
     */
    public boolean existeClave(String clave, HashMap hashMapRecuperado) {
        return hashMapRecuperado.containsKey(clave.toLowerCase().trim());
    }

    /**
     * Verifica si existe una clave dentro del archivo cconfig
     *
     * @param clave la clave a buscar
     * @return true si existe la clave, false en cualquier otro caso
     */
    public boolean existeClave(String clave) {
        return leerArchivo().containsKey(clave.toLowerCase().trim());
    }

    /**
     * Escribe o remplaza un par clave-valor dentro del archivo cconfig
     *
     * @param clave la clave
     * @param valor el valor
     * @return true si se escribio con exito, false en cualquier otro caso
     */
    public boolean escribirClaveValor(String clave, String valor) {
        HashMap<String, String> hashMapRecuperado = new ManejoArchivo().leerArchivo();

        if (existeClave(clave, hashMapRecuperado)) {
            removeClaveValor(clave.toLowerCase().trim());
        }

        hashMapRecuperado.put(clave.toLowerCase().trim(), valor.trim());

        return escribirArchivo(hashMapRecuperado);
    }

    /**
     * Escribe un HashMap en el archivo en la ruta por defecto.
     *
     * @param map el HashMap con la informacion
     *
     * @return true si el HashMap se escribe correctamente en el archivo, false
     * en caso contrario.
     */
    private boolean escribirArchivo(HashMap map) {
        try (FileOutputStream archivo = new FileOutputStream(rutaArchivo); ObjectOutputStream objetoSalida = new ObjectOutputStream(archivo)) {
            objetoSalida.writeObject(map);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al escribirClaveValor: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Lee el contenido del archivo y recupera un HashMap<String, String>
     * almacenado en él.
     *
     * @return el HashMap<String, String> recuperado del archivo, o un HashMap
     * vacío si ocurre un error.
     */
    public HashMap<String, String> leerArchivo() {

        if (!existeArchivo()) {
            return new HashMap<>();
        }

        try (ObjectInputStream objetoEntrada = new ObjectInputStream(new FileInputStream(rutaArchivo))) {

            if (objetoEntrada == null) {
                return new HashMap<>();
            }

            HashMap<String, String> hashMapRecuperado = (HashMap<String, String>) objetoEntrada.readObject();

            if (hashMapRecuperado != null) {
                return hashMapRecuperado;
            }

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error al leerArchivo: " + ex.getMessage());
        }
        return new HashMap<>();
    }

    /**
     * Elimina un par clave-valor, si existe la clave
     *
     * @param clave a eliminar
     * @return true si se elimino, false en cualquier otro caso
     */
    public boolean removeClaveValor(String clave) {
        HashMap<String, String> h = leerArchivo();
        if (!existeClave(clave.toLowerCase(), h)) {
            System.out.println("No existe la clave que se esta intentado eliminar");
            return false;
        }

        h.remove(clave.toLowerCase().trim());

        return escribirArchivo(h);
    }

    /**
     * Lee el valor de una clave dentro del archivo cconfig
     *
     * @param clave del valor
     * @return el valor si existe la clave, "" en caso contrario
     */
    public String leerClaveValor(String clave) {
        HashMap<String, String> h = leerArchivo();

        if (!existeClave(clave.toLowerCase(), h)) {
            System.out.println("No existe la clave que se esta intentado leer");
            return "";
        }

        return h.get(clave.toLowerCase().trim());

    }

    /*
    public static void main(String[] args) {
        ManejoArchivo m = new ManejoArchivo();
        m.escribirClaveValor("nombreusuario", "hugo2");
        m.escribirClaveValor("idempleado", "1");
        System.out.println(m.leerArchivo());
    }
     */
}
