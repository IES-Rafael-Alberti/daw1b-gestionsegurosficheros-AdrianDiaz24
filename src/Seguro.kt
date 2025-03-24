abstract class Seguro(numPoliza: Int, val dniTitular: String, private val importe: Double) {

    init {
        require(dniTitular.matches(Regex("^[0-9]{8}+[A-Z]{1}$"))) {throw IllegalArgumentException("El DNI no cumple el formato basico")}
    }

    open fun calcuarImporteAnioSiguiente(interes: Double):Double{
        return importe * (1 + (interes/100))
    }

    abstract fun tipoSeguro(): String


    fun serializar(): String{

    }

}
