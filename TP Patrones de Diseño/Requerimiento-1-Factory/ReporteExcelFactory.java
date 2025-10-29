/**
 * Implementación de Creador Concreto (Concrete Creator) para reportes Excel.
 *
 * Esta clase implementa la interfaz 'ReporteFactory' y se especializa
 * en la creación de objetos 'ReporteExcel'.
 */
public class ReporteExcelFactory implements ReporteFactory {

    /**
     * Implementación del "Factory Method" (Método de Fábrica).
     *
     * Sobrescribe el método de la interfaz para proveer la lógica
     * específica de instanciación de un 'ReporteExcel'.
     *
     * @return una nueva instancia de 'ReporteExcel'.
     */
    @Override
    public Reporte crearReporte()
    {
        System.out.println("CREAR REPORTE EXCEL!!!\n");

        // Esta hija decide crear un ReporteExcel
        return new ReporteExcel();
    }
}