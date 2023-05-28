package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.*;

/**
 *
 * @author cesar
 */
public class GsonManejador {

    private final Path path = Paths.get("");
    private final String rutaActual = path.toAbsolutePath().toString();
    private final String nombreArchivo = "config.json";
    private final String rutaArchivo = rutaActual + "/src/configuraciones/" + nombreArchivo;

    /**
     *
     */
    public GsonManejador() {
        if (!existeArchivo()) {
            crearArchivo();
        }
    }

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

    public boolean escribirClaveValor(String clave, String valor) {
        JSONObject jo = leerJson();

        if (jo.has(clave)) {
            jo.remove(clave);
        }

        jo.put(clave, valor);

        return escribirJson(jo);
    }

    public Object leerClaveValor(String clave) {
        return leerJson().get(clave);
    }

    public JSONObject leerJson() {

        try {
            String contenidoJSON = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            JSONObject jsonObject = new JSONObject(contenidoJSON);

            return jsonObject;

        } catch (IOException | JSONException ex) {
            System.out.println("Error al leerJson: " + ex.getMessage());
        }

        return new JSONObject();
    }

    public boolean escribirJson(JSONObject jo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {
            escritor.write(jo.toString());
            return true;
        } catch (IOException ex) {
            System.out.println("Error al escribirJson: " + ex.getMessage());
        }
        return false;
    }
    /*
    public static void main(String[] args) {
        GsonManejador gm = new GsonManejador();

        JSONObject jo = new JSONObject();

        jo.put("Prueba", "hola");
        jo.put("Prueba2", 1);

        //leer todo
        //System.out.println(gm.leerJson());
        //leer un valor
        //System.out.println(gm.leerClaveValor("idEmpleado"));
        //escribir un json
        //System.out.println(gm.escribirJson(new JSONObject().put("Prueba", "hola")));
        //escribir un clave valor
        System.out.println(gm.escribirClaveValor("prueba3", "hola"));
    }
     */

}
