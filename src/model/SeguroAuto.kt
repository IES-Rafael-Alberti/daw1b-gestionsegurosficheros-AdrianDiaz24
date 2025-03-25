package model

class SeguroAuto(
    dniTitular: String,
    importe: Double,
    descripcion: String,
    combustible: String,
    tipoAuto: Auto,
    tipoCobertura: String,
    asistenciaCarretera: Boolean,
    var numPartes: Int = 0
): Seguro(generarNumPoliza(), dniTitular, importe) {

    companion object {
        var id = 40000
        fun generarNumPoliza(): Int{
            return id++
        }
    }

    override fun calcuarImporteAnioSiguiente(interes: Double): Double {
        if (numPartes == 0) {
            return super.calcuarImporteAnioSiguiente(interes)
        } else {
            return (super.calcuarImporteAnioSiguiente(interes) * (1 + (0.02 * numPartes)))
        }
    }

    override fun tipoSeguro(): String {
        return "Seguro de Automovil"
    }

}