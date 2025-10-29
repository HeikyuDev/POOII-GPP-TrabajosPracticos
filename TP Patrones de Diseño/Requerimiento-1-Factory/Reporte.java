/**
 * Interfaz "Producto" (Product).
 * Define el contrato común que todos los renderizadores específicos deben seguir.
 */
public interface Reporte {

    /**
     * Devuelve la extensión de archivo predeterminada para este formato.
     * (ej. ".pdf", ".xlsx")
     */
    String getExtensionPredeterminada();
}