public class GestorConfiguracion {

    // --- Parte 1: Atributos del Patrón Singleton ---

    /**
     * 1. La única instancia (privada y estática)
     */
    private static GestorConfiguracion instancia;

    // --- Parte 2: Atributos de la Lógica de Negocio (El Requerimiento) ---

    /**
     * Atributos de configuración que este Singleton debe gestionar.
     */
    private String urlBd;
    private String userBd;
    private String pathReportes;

    /**
     * 2. El constructor (privado) para que nadie pueda hacer "new".
     * Aquí es donde cargamos la configuración (en un caso real,
     * se leería de un archivo .properties o .yml).
     */
    private GestorConfiguracion() {
        System.out.println("Singleton: Gestor de Configuración CREADO.");

        // Simulación de carga de datos de configuración
        this.urlBd = "jdbc:mysql://localhost:3306/mi_base_de_datos";
        this.userBd = "admin";
        this.pathReportes = "/opt/app/reportes/";
    }

    /**
     * 3. El método público (estático) para obtener la instancia.
     * Es el único punto de acceso global.
     */
    public static GestorConfiguracion getInstance() {
        // Si no existe, la crea. Si ya existe, devuelve la misma.
        if (instancia == null) {
            instancia = new GestorConfiguracion();
        }
        return instancia;
    }

    // --- Parte 3: Métodos Públicos (Getters) para la Lógica de Negocio ---

    /**
     * Métodos públicos para que el resto de la aplicación
     * pueda LEER los valores de configuración.
     */

    public String getUrlBd() {
        return urlBd;
    }

    public String getUserBd() {
        return userBd;
    }

    public String getPathReportes() {
        return pathReportes;
    }

}