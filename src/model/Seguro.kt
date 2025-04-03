package model

import redondear

abstract class Seguro(val numPoliza: Int, private val dniTitular: String, protected val importe: Double): IExportable {


    init {
        require(dniTitular.matches(Regex("^[0-9]{8}+[A-Z]{1}$"))) {throw IllegalArgumentException("El DNI no cumple el formato basico")}
    }

    abstract fun calcuarImporteAnioSiguiente(interes: Double):Double

    fun tipoSeguro(): String {
        return this::class.simpleName?:"Desconocido"
    }

    override fun serializar(separador: String): String {
        return "$numPoliza$separador$dniTitular$separador$importe"
    }

    override fun toString(): String {
        return "${this::class.simpleName} - numPoliza=$numPoliza, dniTitular=$dniTitular, importe=${importe.redondear(2)}"
    }

    override fun hashCode(): Int {
        return numPoliza.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return false
        if (other !is Seguro) return false
        if (this.numPoliza == other.numPoliza) return true
        return true
    }


}
