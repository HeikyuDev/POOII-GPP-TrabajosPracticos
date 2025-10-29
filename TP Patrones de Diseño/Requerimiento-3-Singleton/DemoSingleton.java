/**
 * Esta es la clase "main" para probar el patrón Singleton.
 * Se ejecutará por separado del main del Factory.
 */
public class DemoSingleton {

    public static void main(String[] args) {
        
        System.out.println("--- Demo del Patrón Singleton ---");

        // 1. Creamos los módulos
        ModuloFinanzas finanzas = new ModuloFinanzas();
        ModuloMarketing marketing = new ModuloMarketing();

        // 2. Cada módulo usa el Singleton internamente
        finanzas.cargarConfiguracion(); 
        marketing.aplicarTema();

        // 3. Comprobación final
        GestorConfiguracion configFinanzas = finanzas.getConfig();
        GestorConfiguracion configMarketing = marketing.getConfig();

        // Comparamos las referencias de memoria
        if (configFinanzas == configMarketing) {
            System.out.println("\n¡ÉXITO! Finanzas y Marketing usan la MISMA instancia.");
            System.out.println("Hash Finanzas:   " + configFinanzas.hashCode());
            System.out.println("Hash Marketing:  " + configMarketing.hashCode());
        } else {
            System.out.println("\nERROR: Los módulos usan instancias DIFERENTES.");
        }
    }
}