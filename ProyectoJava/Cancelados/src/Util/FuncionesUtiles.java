package Util;

import java.awt.Color;
import java.awt.Image;
import java.text.Normalizer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Empleado;
import vista.EnvioCorreos;

public class FuncionesUtiles {

    private static String emailFrom = "chechocita@gmail.com";
    private static String passwordFrom = "rxrhrkxsgvdrftxx";
    private String emailTo;
    private String subject;
    private String content;

    private Properties mProperties = new Properties();
    ;
    private Session mSession;
    private MimeMessage mCorreo;

    private static final HashMap<String, String> entidadesFederativas = new HashMap<>();

    public FuncionesUtiles() {
        initEntidadesFederativas();
    }

    public String generarNombreUsuario(Empleado empleado) {
        Random random = new Random();
        int numbers = random.nextInt(900) + 100;
        String username = empleado.getaPaterno() + empleado.getNombre() + getAbreviatura(empleado.getCurp()) + numbers;
        return username;
    }

    private String getAbreviatura(String curp) {
        //System.out.println(curp.substring(11, 13));
        return curp.substring(11, 13);
    }

    public Object[] getEstados() {

        ArrayList estados = new ArrayList<>();
        estados.add("SELECCIONE UN ESTADO");
        estados.addAll(entidadesFederativas.values());

        return estados.toArray();
    }

    private void initEntidadesFederativas() {
        entidadesFederativas.put("AS", "Aguascalientes");
        entidadesFederativas.put("BC", "Baja California");
        entidadesFederativas.put("BS", "Baja California Sur");
        entidadesFederativas.put("CC", "Campeche");
        entidadesFederativas.put("CL", "Coahuila de Zaragoza");
        entidadesFederativas.put("CM", "Colima");
        entidadesFederativas.put("CS", "Chiapas");
        entidadesFederativas.put("CH", "Chihuahua");
        entidadesFederativas.put("DF", "Ciudad de México");
        entidadesFederativas.put("DG", "Durango");
        entidadesFederativas.put("GT", "Guanajuato");
        entidadesFederativas.put("GR", "Guerrero");
        entidadesFederativas.put("HG", "Hidalgo");
        entidadesFederativas.put("JC", "Jalisco");
        entidadesFederativas.put("MC", "México");
        entidadesFederativas.put("MN", "Michoacán de Ocampo");
        entidadesFederativas.put("MS", "Morelos");
        entidadesFederativas.put("NT", "Nayarit");
        entidadesFederativas.put("NL", "Nuevo León");
        entidadesFederativas.put("OC", "Oaxaca");
        entidadesFederativas.put("PL", "Puebla");
        entidadesFederativas.put("QT", "Querétaro");
        entidadesFederativas.put("QR", "Quintana Roo");
        entidadesFederativas.put("SP", "San Luis Potosí");
        entidadesFederativas.put("SL", "Sinaloa");
        entidadesFederativas.put("SR", "Sonora");
        entidadesFederativas.put("TC", "Tabasco");
        entidadesFederativas.put("TS", "Tamaulipas");
        entidadesFederativas.put("TL", "Tlaxcala");
        entidadesFederativas.put("VZ", "Veracruz de Ignacion de la Llave");
        entidadesFederativas.put("YN", "Yucatán");
        entidadesFederativas.put("ZS", "Zacatecas");
        //entidadesFederativas.put("NE", "Nacido en el extranjero");
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
            JOptionPane.showMessageDialog(ventana, "ERROR, Debe de ingresar una edad valida en el RFC, la edad debe de ser: " + edadMinima + "<= edad <=" + edadMaxima);
            //System.out.println("La edad del empleado debe de ser: 18<=X<=65");
            return false;
        }

        //Primera letra el apellido paterno
        char iPaterno = empleado.getaPaterno().toUpperCase().charAt(0);
        //Primera vocal inicial del apellidoPaterno
        char vPaterno = getVocal(empleado.getaPaterno().substring(1).toUpperCase());
        //Primera letra inicial del apellidoMaterno
        char iMaterno = empleado.getaMaterno().toUpperCase().charAt(0);
        //Primera letra inicial del nombre
        char iNombre = empleado.getNombre().toUpperCase().charAt(0);
        //Primeros 4 caracteres del RFC
        char[] inicialesRFC = rfc.substring(0, 4).toUpperCase().toCharArray();

        //Verificar si las iniciales coninciden con las del RFC
        if (!verificarIniciales(inicialesRFC, iPaterno, vPaterno, iMaterno, iNombre)) {
            JOptionPane.showMessageDialog(ventana, "ERROR las iniciales no concuerdan con las del RFC");
            //System.out.println("ERROR las iniciales no concuerdan con las del RFC");
            return false;
        }

        return true;
    }

    private boolean verificarIniciales(char[] rfc, char iPaterno, char vPaterno, char iMaterno, char iNombre) {

        //Verificar que la vocal de vPaterno no sea nula
        if (vPaterno == '\0') {
            return false;
        }

        char[] iniciales = {iPaterno, vPaterno, iMaterno, iNombre};

        //System.out.println(iniciales);
        //System.out.println(rfc);
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

    private static boolean validarOrdenCURP(String curp) {
        return !curp.matches("^[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[A-Z0-9]{2}$");
    }

    private static boolean verificarEntidadFederativa(String abreviatura, String entidadFederativa) {
        String nombre = entidadesFederativas.get(abreviatura);
        return nombre != null && nombre.equals(entidadFederativa);
    }

    public boolean validarCurp(Empleado empleado, int edadMinima, int edadMaxima, JFrame ventana) {

        //Obtener la CURP
        String curp = empleado.getCurp();

        //Validar longitud
        if (curp.length() != 18) {
            JOptionPane.showMessageDialog(ventana, "ERROR! La cantidad de caracteres de la CURP es incorrecto, debe de ser igual a 18");
            return false;
        }

        //Validar caracteres y orden de caracteres
        if (validarOrdenCURP(curp)) {
            JOptionPane.showMessageDialog(ventana, "ERROR! El orden de los caracteres de la CURP es incorrecto");
            return false;
        }

        //Obtener fecha de nacimiento
        String fechaNacimiento = curp.substring(4, 10);

        //Verificar que la fecha de nacimiento sea 18<=X<=65
        if (!verificarEdad(fechaNacimiento, edadMinima, edadMaxima)) {
            JOptionPane.showMessageDialog(ventana, "ERROR, Debe de ingresar una edad valida en la CURP, la edad debe de ser: " + edadMinima + "<= edad <=" + edadMaxima);
            return false;
        }

        //Primera letra el apellido paterno
        char iPaterno = empleado.getaPaterno().toUpperCase().charAt(0);
        //Primera vocal inicial del apellidoPaterno
        char vPaterno = getVocal(empleado.getaPaterno().substring(1).toUpperCase());
        //Primera letra inicial del apellidoMaterno
        char iMaterno = empleado.getaMaterno().toUpperCase().charAt(0);
        //Primera letra inicial del nombre
        char iNombre = empleado.getNombre().toUpperCase().charAt(0);
        //Primeros 4 caracteres del RFC
        char[] inicialesCURP = curp.substring(0, 4).toUpperCase().toCharArray();

        //Verificar si las iniciales coninciden con las de la CURP
        if (!verificarIniciales(inicialesCURP, iPaterno, vPaterno, iMaterno, iNombre)) {
            JOptionPane.showMessageDialog(ventana, "ERROR las iniciales no concuerdan con las de la CURP");
            return false;
        }

        //Obtener la entidad federativa de la CURP
        String abreviatura = curp.substring(11, 13);
        //Obtener entidad federativa
        String entidadFederativa = empleado.getEstado();

        //Verificar que la entidad federativa exista
        if (!verificarEntidadFederativa(abreviatura, entidadFederativa)) {
            JOptionPane.showMessageDialog(ventana, "ERROR la entidad federativa de la CURP no es valida o no concuerda con la ingresada en 'Estado'");
            return false;
        }

        //Primer consonante interna del apellido paterno
        char cPaterno = getConsonanteInicial(empleado.getaPaterno().toUpperCase());
        //Primer consonante interna del apellido materno
        char cMaterno = getConsonanteInicial(empleado.getaMaterno().toUpperCase());
        //Primer consonante interna del nombre
        char cNombre = getConsonanteInicial(empleado.getNombre().toUpperCase());
        //Obtener las consonantes de la CURP
        char[] consonantesCURP = curp.substring(13, 16).toCharArray();

        if (!verificarConsonantes(consonantesCURP, cPaterno, cMaterno, cNombre)) {
            JOptionPane.showMessageDialog(ventana, "ERROR las consonantes de la curp y las encontradas en el nombre completo no concuerda");
            return false;
        }

        return true;
    }

    public static char removerAcentos(char letra) {

        if (letra == 'Ñ') {
            return letra;
        }

        String textoNormalizado = Normalizer.normalize(String.valueOf(letra), Normalizer.Form.NFD);
        textoNormalizado = textoNormalizado.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return textoNormalizado.charAt(0);
    }

    private static boolean verificarConsonantes(char[] consonantesCURP, char cPaterno, char cMaterno, char cNombre) {

        //Verificar que las consonantes no sean nulas
        if (cPaterno == '\0' || cMaterno == '\0' || cNombre == '\0') {
            return false;
        }

        char[] iniciales = {cPaterno, cMaterno, cNombre};

        return Arrays.equals(consonantesCURP, iniciales);
    }

    private static char getConsonanteInicial(String cad) {

        // Ignora el primer carácter
        String subcadena = cad.substring(1);

        //Convertir a char[]
        char[] letras = subcadena.toCharArray();

        for (char letra : letras) {
            if (!esVocal(removerAcentos(letra))) {
                return letra;
            }
        }

        // Retorna el carácter nulo si no se encontró ninguna consonante
        return '\0';
    }

    private static boolean esVocal(char c) {
        c = Character.toUpperCase(c);
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    /**
     * Funcion para enviar un correo electronico
     *
     * @param emailTo correo de destino
     * @param subject asunto del correo
     * @param content contenido del correo
     */
    public void enviarCorreo(String emailTo, String subject, String content) {
        this.emailTo = emailTo.trim();
        this.subject = subject.trim();
        this.content = subject.trim();

        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "ISO-8859-1", "html");

        } catch (AddressException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }

        sendEmail();

    }

    /**
     * Funcion para enviar un correo electronico de bienvenida con un asunto y
     * contenido por defecto.
     *
     * @param emailTo correo de destino
     * @param nombreCompletoEmpleado nombre completo del empleado
     * @param nombreUsuario el nombre de usuario del empleado
     */
    public void enviarCorreoMensajePredefinido(String emailTo, String nombreCompletoEmpleado, String nombreUsuario) {
        this.emailTo = emailTo.trim();
        this.subject = "¡Bienvenido a la empresa Cancelados! Tu acceso al sistema ha sido configurado.";
        this.content = "Estimado [" + nombreCompletoEmpleado + "],\n\n"
                + "¡En nombre de todo el equipo de la empresa Cancelados, queremos darte una cálida bienvenida! Estamos encantados de tenerte como parte de nuestra organización y nos complace informarte que tu registro en nuestro sistema de información ha sido completado satisfactoriamente.\n\n"
                + "Tu compromiso y talento son valiosos para nosotros, y nos complace brindarte todas las herramientas necesarias para que puedas comenzar a contribuir a nuestro éxito conjunto. A continuación, encontrarás los detalles de tu cuenta de usuario para que puedas acceder al sistema:\n\n"
                + "Nombre de usuario: [" + nombreUsuario + "]\n\n"
                + "Te recomendamos que cambies tu contraseña al ingresar por primera vez para asegurar la confidencialidad de tu cuenta.\n\n"
                + "Una vez más, te damos la bienvenida a la empresa Cancelados. ¡Estamos emocionados de comenzar esta nueva etapa junto a ti y deseamos que tengas mucho éxito en tu trayectoria con nosotros!";

        // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content, "UTF-8", "plain");

        } catch (AddressException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }

        sendEmail();

    }

    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            JOptionPane.showMessageDialog(null, "Correo enviado");

        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    public static void main(String[] args) {

        Empleado empleado = new Empleado();

        empleado.setNombre("Juan");
        empleado.setaPaterno("García");
        empleado.setaMaterno("López");
        empleado.setCalle("Calle Juarez");
        empleado.setNoExt("111");
        empleado.setColonia("San Pedro");
        empleado.setCp("12345");
        empleado.setCurp("GALJ580311HDFRPN00");
        empleado.setRfc("GALJ580311N77");
        empleado.setMunicipio("Ciudad de México");
        empleado.setEstado("Ciudad de México");
        empleado.setIdRol(2);

        //System.out.println(new FuncionesUtiles().validarRFC(empleado, 18, 65, new javax.swing.JFrame()));
        System.out.println(new FuncionesUtiles().validarCurp(empleado, 18, 65, new javax.swing.JFrame()));
    }
     */
    public static void main(String[] args) {
        FuncionesUtiles tool = new FuncionesUtiles();

        String emailTo = "cesarinzunsa@gmail.com";
        String nombreCompletoEmpleado = "Juanito Perez";
        String nombreUsuario = "usuarioPrueba";

        tool.enviarCorreoMensajePredefinido(emailTo, nombreCompletoEmpleado, nombreUsuario);
    }
}
