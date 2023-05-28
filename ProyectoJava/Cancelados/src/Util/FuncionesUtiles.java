package Util;

import java.awt.Color;
import java.awt.Image;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Empleado;

public class FuncionesUtiles {

    public FuncionesUtiles() {

    }

    /**
     * Funcion que coloca una imagen en un jButton pero que no lo ajusta al
     * tamaño del boton
     *
     * @param imagen
     * @param jButton
     */
    public void colocarImagen(String imagen, JButton jButton) {
        ImageIcon img = new ImageIcon(getClass().getResource(imagen));
        jButton.setIcon(img);
    }

    /**
     * Funcion que coloca una imagen en un jlabel y la justa deacuerdo al tamaño
     * del jlabel.
     *
     * @param imagen
     * @param jLabel
     */
    public void colocarImagen(String imagen, JLabel jLabel) {
        ImageIcon img = new ImageIcon(getClass().getResource(imagen));
        ImageIcon imgPequeno = escalarImagen(img, jLabel);
        jLabel.setIcon(imgPequeno);
    }

    /**
     * Funcion que escala una imagen para que se acomode deacuerdo al tamaño del
     * jlabel
     *
     * @param img
     * @param donde
     * @return
     */
    public ImageIcon escalarImagen(ImageIcon img, JLabel donde) {
        Image imgLabel = img.getImage().getScaledInstance(donde.getWidth(), donde.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(imgLabel);
    }

    /**
     * Funcion que ayuda a configurar los botones que van a la izquierda de cada
     * pantalla. Elimina el fondo y alinea el texto a la izquierda.
     *
     * @param boton
     */
    public void confBtnMenu(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
    }

    /**
     * Funcion que quita el fondo a los botones y deja centrado el texto.
     *
     * @param boton
     */
    public void confBtn(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Funcion pinta el fondo de un boton del color (94,29,27), y cambia el
     * color de las letras a blanco.
     *
     * @param boton
     */
    public void confBtnColor(JButton boton) {
        boton.setBackground(getColorCancelados());
        boton.setForeground(Color.WHITE);
    }

    /**
     * Returna el color 94,29,27 que es ese rojo tinto
     *
     * @return Color(94, 29, 27);
     */
    public Color getColorCancelados() {
        return new Color(94, 29, 27);
    }

    /**
     * retorna la hora actual en formato de 24 horas como un string
     *
     * @return String
     */
    public String getHora() {
        Calendar calendario = GregorianCalendar.getInstance();
        String cad = "";

        int hora = calendario.get(Calendar.HOUR_OF_DAY) - 1;

        if (hora < 10) {
            cad += "0" + hora + ":";
        } else {
            cad += hora + ":";
        }

        int minuto = calendario.get(Calendar.MINUTE);
        if (minuto < 10) {
            cad += "0" + minuto;
        } else {
            cad += minuto;
        }

        return cad;

    }

    /**
     * Retorna la fecha actual: dia nombre del mes y el año.
     *
     * @return String
     */
    public String getFecha() {
        Calendar calendario = GregorianCalendar.getInstance();
        String cad = "";
        String[] monthName = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        if (calendario.get(Calendar.DATE) < 10) {
            cad += "0" + calendario.get(Calendar.DATE) + " de ";
        } else {
            cad += "" + calendario.get(Calendar.DATE) + " de ";
        }

        cad += monthName[calendario.get(Calendar.MONTH)] + " del ";
        cad += calendario.get(Calendar.YEAR);

        return cad;
    }

    /**
     * Retorna la fecha actual formateada como una cadena en el formato
     * "yyyy-MM-dd".
     *
     * @return La fecha actual formateada como una cadena.
     */
    public String formatoFecha() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formato);
        return fechaFormateada;
    }

    private static boolean validarOrdenRFC(String rfc) {
        String regex = "^[A-Z]{4}[0-9]{6}[A-Z0-9]{3}$";
        return !rfc.matches(regex);
    }

    public boolean validarRFC(Empleado empleado, int edadMinima, int edadMaxima, JFrame ventana) {

        //Obtener el RFC
        String rfc = empleado.getRfc();

        //Validar longitud
        if (rfc.length() != 13) {
            JOptionPane.showMessageDialog(ventana, "ERROR! La cantidad de caracteres del RFC es incorrecto, debe de ser igual a 13");
            return false;
        }

        //Validar caracteres y orden de caracteres
        if (validarOrdenRFC(rfc)) {
            JOptionPane.showMessageDialog(ventana, "ERROR! El orden de los caracteres del RFC es incorrecto");
            //System.out.println("ERROR! El orden de los caracteres es incorrecto");
            return false;
        }

        //Obtener fecha de nacimiento
        String fechaNacimiento = rfc.substring(4, 10);

        //Verificar que la fecha de nacimiento sea 18<=X<=65
        if (!verificarEdad(fechaNacimiento, edadMinima, edadMaxima)) {
            JOptionPane.showMessageDialog(ventana, "ERROR, Debe de ingresar una edad valida en el RFC, la edad debe de ser: 18<=X<=65");
            //System.out.println("La edad del empleado debe de ser: 18<=X<=65");
            return false;
        }

        //Primera letra el apellido paterno
        char iPaterno = empleado.getaPaterno().toUpperCase().charAt(0);
        //Primera vocal inicial del apellidoPaterno
        char vPaterno = getVocal(empleado.getaPaterno().toUpperCase());
        //Primera letra inicial del apellidoMaterno
        char iMaterno = empleado.getaMaterno().toUpperCase().charAt(0);
        //Primera letra inicial del nombre
        char iNombre = empleado.getNombre().toUpperCase().charAt(0);
        //Primeros 4 caracteres del RFC
        char[] inicialesRFC = rfc.substring(0, 4).toUpperCase().toCharArray();

        //Verificar si las iniciales coninciden con las del RFC
        if (!verificarInicialesRFC(inicialesRFC, iPaterno, vPaterno, iMaterno, iNombre)) {
            JOptionPane.showMessageDialog(ventana, "ERROR las iniciales no concuerdan con las del RFC");
            //System.out.println("ERROR las iniciales no concuerdan con las del RFC");
            return false;
        }

        return true;
    }

    private boolean verificarInicialesRFC(char[] rfc, char iPaterno, char vPaterno, char iMaterno, char iNombre) {

        //Verificar que la vocal de vPaterno no sea nula
        if (vPaterno == '\0') {
            return false;
        }

        char[] iniciales = {iPaterno, vPaterno, iMaterno, iNombre};
        return Arrays.equals(rfc, iniciales);
    }

    private boolean verificarEdad(String rfc, int edadMinima, int edadMaxima) {
        char[] fechaNacimiento = rfc.toCharArray();

        try {
            if (fechaNacimiento.length != 6) {
                System.out.println("El arreglo debe tener 6 caracteres (aammdd).");
                return false;
            }

            int year = Integer.parseInt(new String(fechaNacimiento, 0, 2));
            int month = Integer.parseInt(new String(fechaNacimiento, 2, 2));
            int day = Integer.parseInt(new String(fechaNacimiento, 4, 2));

            int currentYear = LocalDate.now().getYear();
            int currentMonth = LocalDate.now().getMonthValue();
            int currentDay = LocalDate.now().getDayOfMonth();

            // Obtener el siglo actual
            int century = currentYear / 100;

            // Determinar el siglo adecuado para el año de nacimiento del RFC
            int birthCentury = century * 100;
            if (currentYear % 100 < year) {
                birthCentury -= 100;
            }

            LocalDate birthDate = null;

            try {
                birthDate = LocalDate.of(birthCentury + year, month, day);
            } catch (Exception e) {
                return false;
            }

            LocalDate currentDate = LocalDate.of(currentYear, currentMonth, currentDay);

            Period age = Period.between(birthDate, currentDate);
            int years = age.getYears();

            //System.out.println(years);
            return years >= edadMinima && years <= edadMaxima;
        } catch (NumberFormatException e) {
            System.out.println("Error en esMayorEdad: " + e.getMessage());
        }
        return false;
    }

    private char getVocal(String cadena) {
        Pattern patron = Pattern.compile("[aeiouAEIOU]");
        Matcher matcher = patron.matcher(cadena);
        if (matcher.find()) {
            return matcher.group().charAt(0);
        } else {
            return '\0'; // Retornar el caracter nulo si no se encuentra ninguna vocal
        }
    }
    /*
    public static void main(String[] args) {

        //DATOS CORRECTOS DE PRUEBA
        Empleado empleado = new Empleado();

        empleado.setNombre("Juan");
        empleado.setaPaterno("García");
        empleado.setaMaterno("López");
        empleado.setCalle("Calle Juarez");
        empleado.setNoExt("111");
        empleado.setColonia("San Pedro");
        empleado.setCp("12345");
        empleado.setCurp("GALJ920101HDFXXX00");
        empleado.setRfc("GALJ585027N77");
        empleado.setMunicipio("Ciudad de México");
        empleado.setEstado("Ciudad de México");
        empleado.setIdRol(2);

        FuncionesUtiles fun = new FuncionesUtiles();

        System.out.println(fun.validarRFC(empleado, 18, 65, new javax.swing.JFrame()));

    }
     */
}
