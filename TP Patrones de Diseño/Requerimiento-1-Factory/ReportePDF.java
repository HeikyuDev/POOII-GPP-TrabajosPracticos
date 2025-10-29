

public class ReportePDF implements Reporte {

    /**
     * Metodo que se va a sobreescribir para 
     * indicar la extension del Reporte, en este caso pdf
     * @return la Extension del Reporte
     */
    @Override
    public String getExtensionPredeterminada()
    {
        return ".pdf";
    }
}
