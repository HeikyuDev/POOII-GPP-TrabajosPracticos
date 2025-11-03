classDiagram
    IServicioEnvio <|.. LogisticaVelozAdapter
    LogisticaVelozAdapter --> ApiLogisticaVeloz
    ApiLogisticaVeloz --> Cotizacion
    ApiLogisticaVeloz --> DatosEnvio

    class IServicioEnvio {
        +calcularCosto(String)
        +obtenerTiempoEstimado(String)
        +despacharPedido(String, String, String)
    }

    class LogisticaVelozAdapter {
        -ApiLogisticaVeloz api
        +calcularCosto(String)
        +obtenerTiempoEstimado(String)
        +despacharPedido(String, String, String)
    }

    class ApiLogisticaVeloz {
        +cotizarEnvio(int)
        +enviarPaquete(DatosEnvio)
    }

    class Cotizacion {
        -float costo
        -int dias
        +getCosto()
        +getDias()
    }

    class DatosEnvio {
        -String direccion
        -int cpDestino
        -String idPedido
    }
