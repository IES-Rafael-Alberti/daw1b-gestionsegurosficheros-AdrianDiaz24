package model

abstract class Seguro(val numPoliza: Int, private val dniTitular: String, protected val importe: Double): IExportable {


    init {
        require(dniTitular.matches(Regex("^[0-9]{8}+[A-Z]{1}$"))) {throw IllegalArgumentException("El DNI no cumple el formato basico")}
    }

    open fun calcuarImporteAnioSiguiente(interes: Double):Double {
        return importe * (1 + (interes / 100))
    }

    abstract fun tipoSeguro(): String

    override fun serializar(separador: String): String {
        return "$numPoliza$separador$dniTitular$separador$importe"
    }

}
