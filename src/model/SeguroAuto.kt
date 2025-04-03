package model

class SeguroAuto : Seguro {

    companion object {

        var id = 40000

        fun generarNumPoliza(): Int{
            return id++
        }

        val porcentajeIncrementoPartes = 2

        fun crearSeguroAuto(datos: List<String>): SeguroAuto {
            if (datos.size < 8) {throw IllegalArgumentException("**ERROR** Debes pasar una lista de String con los siguientes datos en este orden DNI del titular, Importe, Descripcion, Combustible, Tipo de auto, Tipo de cobertura, asistencia en carretera(true/false) y Nº de partes")}
            try {
                return SeguroAuto(datos[0], datos[1].toDouble(), datos[2], datos[3], Auto.getAuto(datos[4]), Cobertura.getCobertura(datos[5]), datos[6].toBoolean(), datos[7].toInt())
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException("**ERROR** Argumentos intoducidos no coincinden con los cuales deberian ser")
            } catch (e: Exception) {
                throw Exception("**UNEXPECTED ERROR** $e")
            }
        }

    }

    private var descripcion = ""
    private var combustible = ""
    private var tipoAuto = Auto.COCHE
    private var tipoCobertura = Cobertura.TERCEROS
    private var asistenciaCarretera = false
    private var numPartes = 0

    constructor(
        dniTitular: String,
        importe: Double,
        descripcionIntroducida: String,
        combustibleIntroducido: String,
        tipoAutoIntroducido: Auto,
        tipoCoberturaIntroducida: Cobertura,
        asistenciaCarreteraIntroducida: Boolean,
        numPartesIntroducidos: Int = 0
    ): super(generarNumPoliza(), dniTitular, importe) {
        descripcion = descripcionIntroducida
        combustible = combustibleIntroducido
        tipoAuto = tipoAutoIntroducido
        tipoCobertura = tipoCoberturaIntroducida
        asistenciaCarretera = asistenciaCarreteraIntroducida
        numPartes = numPartesIntroducidos
    }

    private constructor(
        numPoliza: Int,
        dniTitular: String,
        importe: Double,
        descripcionIntroducida: String,
        combustibleIntroducido: String,
        tipoAutoIntroducido: Auto,
        tipoCoberturaIntroducida: Cobertura,
        asistenciaCarreteraIntroducida: Boolean,
        numPartesIntroducidos: Int = 0
    ): super(numPoliza, dniTitular, importe) {
        descripcion = descripcionIntroducida
        combustible = combustibleIntroducido
        tipoAuto = tipoAutoIntroducido
        tipoCobertura = tipoCoberturaIntroducida
        asistenciaCarretera = asistenciaCarreteraIntroducida
        numPartes = numPartesIntroducidos
    }

    override fun calcuarImporteAnioSiguiente(interes: Double): Double {
        var interes = interes/100
        if (numPartes == 0) {
            return importe * (1 + interes)
        } else {
            return importe * (1 + (interes + ((porcentajeIncrementoPartes/100) * numPartes)))
        }
    }

    override fun serializar(separador: String): String {
        return super.serializar(separador) + "$separador$descripcion$separador$combustible$separador$tipoAuto$separador$tipoCobertura$separador$asistenciaCarretera$separador$numPartes$separador${tipoSeguro()}"
    }

    override fun toString(): String {
        return super.toString() +", descripcion=$descripcion, combustible=$combustible, tipoAuto=$tipoAuto, tipoCorbertura=$tipoCobertura, asistenciaEnCarretera=${if (asistenciaCarretera == true) "Si" else "No"}, numPartes=$numPartes"
    }

}