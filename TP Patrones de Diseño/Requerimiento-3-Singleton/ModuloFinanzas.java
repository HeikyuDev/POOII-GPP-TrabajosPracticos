public class ModuloFinanzas {
    
    private GestorConfiguracion config;

    public ModuloFinanzas() {
        // El módulo pide la instancia única al crear
        this.config = GestorConfiguracion.getInstance();
    }

    public void cargarConfiguracion() {
        
        
        // AHORA: Usamos los getters para obtener los datos reales
        System.out.println("--- Módulo Finanzas ---");
        System.out.println("Conectando a la base de datos con:");
        System.out.println("URL: " + config.getUrlBd());
        System.out.println("User: " + config.getUserBd());
    }
    
    public GestorConfiguracion getConfig() {
        return this.config;
    }
}