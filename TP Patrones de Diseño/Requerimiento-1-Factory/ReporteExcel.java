

public class ReporteExcel implements Reporte{
    /**
     * Metodo que se va a sobreescribir para 
     * indicar la extension del Reporte, en este caso Excel
     * @return la Extension del Reporte
     */
    @Override
    public String getExtensionPredeterminada()
    {
        return ".xlsx";
    }
}
