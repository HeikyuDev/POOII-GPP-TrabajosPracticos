// Clase principal que representa el sistema de E-Commerce
public class SistemaECommerce {

    private IServicioEnvio servicioEnvio;

    public SistemaECommerce(IServicioEnvio servicioEnvio) {
        this.servicioEnvio = servicioEnvio;
    }

    public void procesarPedido(String destino, double peso) {
        double costo = servicioEnvio.calcularCosto(destino, peso);
        int tiempo = servicioEnvio.obtenerTiempoEstimado(destino);
        System.out.println("Costo de envío: $" + costo);
        System.out.println("Tiempo estimado: " + tiempo + " días");
        servicioEnvio.despacharPedido(destino, peso);
    }
}
