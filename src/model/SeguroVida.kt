package model

class SeguroVida(dniTitular: String, importe: Double, fechaNac: String,var nivelRiesgo: Riesgo, indemnizacion: Double): Seguro(generarNumPoliza(), dniTitular, importe) {

    companion object {
        var id = 80000
        fun generarNumPoliza(): Int{
            return id++
        }
    }

    override fun calcuarImporteAnioSiguiente(interes: Double): Double {
        return when(nivelRiesgo) {
            Riesgo.BAJO -> importe * (1.02 + interes)
            Riesgo.MEDIO -> importe * (1.05 + interes)
            Riesgo.ALTO -> importe * (1.10 + interes)
        }
    }



}