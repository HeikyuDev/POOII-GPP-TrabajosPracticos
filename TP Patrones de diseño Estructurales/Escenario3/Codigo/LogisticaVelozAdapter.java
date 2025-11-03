// Adaptador que traduce la interfaz del sistema a la de la API externa
public class LogisticaVelozAdapter implements IServicioEnvio {

    private ApiLogisticaVeloz apiVeloz;

    public LogisticaVelozAdapter(ApiLogisticaVeloz apiVeloz) {
        this.apiVeloz = apiVeloz;
    }

    @Override
    public double calcularCosto(String destino, double peso) {
        // Traduce el método al formato esperado por la API externa
        return apiVeloz.getTarifa(destino, peso);
    }

    @Override
    public int obtenerTiempoEstimado(String destino) {
        // Convierte el formato de "2 días" → 2 (entero)
        String texto = apiVeloz.tiempoEntrega(destino);
        return Integer.parseInt(texto.split(" ")[0]);
    }

    @Override
    public void despacharPedido(String destino, double peso) {
        apiVeloz.enviar(destino, peso);
    }
}
