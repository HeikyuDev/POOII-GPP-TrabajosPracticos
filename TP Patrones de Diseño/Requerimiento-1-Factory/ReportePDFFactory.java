
/**
 * Implementación de Creador Concreto (Concrete Creator) para reportes PDF.
 *
 * Esta clase implementa la interfaz 'ReporteFactory' y se especializa
 * en la creación de objetos 'ReportePDF'.
 */
public class ReportePDFFactory implements ReporteFactory {

    /**
     * Implementación del "Factory Method" (Método de Fábrica).
     *
     * Sobrescribe el método de la interfaz para proveer la lógica
     * específica de instanciación de un 'ReportePDF'.
     *
     * @return una nueva instancia de 'ReportePDF'.
     */
    @Override
    public Reporte crearReporte()
    {
        // Mensaje de log para saber qué fábrica se está usando
        System.out.println("CREAR REPORTE PDF!!!\n");

        // Aquí es donde la "hija" (esta clase) decide qué objeto concreto crear.
        return new ReportePDF();
    }
}