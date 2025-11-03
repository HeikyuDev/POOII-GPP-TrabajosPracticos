public class ApiLogisticaVeloz {

    public double getTarifa(String ciudad, double kilos) {
        return 1500 + (kilos * 250);
    }

    public String tiempoEntrega(String ciudad) {
        if (ciudad.equalsIgnoreCase("Posadas")) return "2 días";
        else return "5 días";
    }

    public void enviar(String ciudad, double kilos) {
        System.out.println("LogísticaVeloz: Enviando " + kilos + "kg a " + ciudad);
    }
}
