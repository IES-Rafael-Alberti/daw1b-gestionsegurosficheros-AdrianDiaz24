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
            Riesgo.BAJO -> super.calcuarImporteAnioSiguiente(interes) * 1.02
            Riesgo.MEDIO -> super.calcuarImporteAnioSiguiente(interes) * 1.05
            Riesgo.ALTO -> super.calcuarImporteAnioSiguiente(interes) * 1.10
        }
    }



}