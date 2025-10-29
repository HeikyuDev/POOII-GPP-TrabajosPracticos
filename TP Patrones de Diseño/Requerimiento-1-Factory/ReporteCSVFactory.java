/**
 * Implementación de Creador Concreto (Concrete Creator) para reportes CSV.
 *
 * Esta clase implementa la interfaz 'ReporteFactory' y se especializa
 * en la creación de objetos 'ReporteCSV'.
 */
public class ReporteCSVFactory implements ReporteFactory{

    /**
     * Implementación del "Factory Method" (Método de Fábrica).
     *
     * Sobrescribe el método de la interfaz para proveer la lógica
     * específica de instanciación de un 'ReporteCSV'.
     *
     * @return una nueva instancia de 'ReporteCSV'.
     */
    @Override
    public Reporte crearReporte()
    {
        System.out.println("CREAR REPORTE CSV!!!\n");

        // Esta hija decide crear un ReporteCSV
        return new ReporteCSV();
    }
}