public class ModuloMarketing {

    private GestorConfiguracion config;

    public ModuloMarketing() {
        // El módulo pide la MISMA instancia única
        this.config = GestorConfiguracion.getInstance();
    }

    public void aplicarTema() {        

        // AHORA: Usamos los getters para obtener los datos reales
        System.out.println("--- Módulo Marketing ---");
        System.out.println("Estableciendo ruta de reportes a:");
        System.out.println("Path: " + config.getPathReportes());
    }

    public GestorConfiguracion getConfig() {
        return this.config;
    }
}