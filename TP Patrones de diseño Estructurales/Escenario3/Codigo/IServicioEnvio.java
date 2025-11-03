// Interfaz esperada por el sistema
public interface IServicioEnvio {
    double calcularCosto(String destino, double peso);
    int obtenerTiempoEstimado(String destino);
    void despacharPedido(String destino, double peso);
}
