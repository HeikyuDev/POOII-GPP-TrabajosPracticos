// Clase de prueba (main)
public class Main {
    public static void main(String[] args) {

        // Instancia de la librería externa
        ApiLogisticaVeloz apiVeloz = new ApiLogisticaVeloz();

        // Adaptador que permite usar la API externa como si fuera parte del sistema
        IServicioEnvio adaptador = new LogisticaVelozAdapter(apiVeloz);

        // Sistema E-Commerce usando el adaptador
        SistemaECommerce sistema = new SistemaECommerce(adaptador);

        // Prueba del flujo completo
        System.out.println("=== Prueba de integración con LogísticaVeloz ===");
        sistema.procesarPedido("Posadas", 4.5);
    }
}
