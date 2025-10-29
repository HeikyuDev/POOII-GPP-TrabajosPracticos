/**
 * Esta es una "Simple Factory". Su único propósito es
 * encapsular la lógica de decisión para seleccionar
 * qué fábrica concreta (PDF, Excel, etc.) debe usarse.
 *
 * Esta es la ÚNICA clase que conoce a ReportePDFFactory, etc.
 */
public class CreadorDeFabricas {

    /**
     * Método estático que devuelve la FÁBRICA DE INTERFAZ
     * basada en la selección del usuario.
     */
    public static ReporteFactory obtenerFactory(int tipoDeReporte) {
        switch (tipoDeReporte) {
            case 1:
                return new ReportePDFFactory();
            case 2:
                return new ReporteExcelFactory();
            case 3:
                return new ReporteCSVFactory();
            default:
                throw new IllegalArgumentException("Tipo de reporte no válido: " + tipoDeReporte);
        }
    }
}