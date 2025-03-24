package model

class SeguroAuto(
    numPoliza: Int,
    dniTitular: String,
    private val importe: Double,
    descripcion: String,
    combustible: String,
    tipoAuto: TipoAuto,
    tipoCobertura: String,
    asistenciaCarretera: Boolean,
    var numPartes: Int = 0
): Seguro(numPoliza, dniTitular, importe) {

    override fun calcuarImporteAnioSiguiente(interes: Double): Double {
        if (numPartes == 0) {
            return super.calcuarImporteAnioSiguiente(interes)
        } else {
            return (super.calcuarImporteAnioSiguiente(interes) * (1 + (0.02 * numPartes)))
        }
    }

    override fun tipoSeguro(): String {
        return "model.Seguro de Automovil"
    }

}