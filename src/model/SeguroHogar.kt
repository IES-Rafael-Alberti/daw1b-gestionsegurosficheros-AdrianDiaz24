package model

class SeguroHogar: Seguro {

    companion object {

        var id = 10000

        private fun generarNumPoliza(): Int{
            return id++
        }


        /**
         *  Funcion para crear nuevos seguro de hogar
         *  @param datos List con que contenga los siguientes datos en forma de String DNI del titular, Importe, Metros Cuadrados, Valor Contenido, Direccion y Años de Construccion
         */
        fun crearSeguroHogar(datos: List<String>): SeguroHogar{
            if (datos.size < 6) {throw IllegalArgumentException("**ERROR** Debes pasar una lista de String con los siguientes datos en este orden DNI del titular, Importe, Metros Cuadrados, Valor Contenido, Direccion y Años de Construccion")}
            try {
                return SeguroHogar(datos[0], datos[1].toDouble(), datos[2].toInt(), datos[3].toDouble(), datos[4], datos[5].toInt())
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException("**ERROR** Argumentos intoducidos no coincinden con los cuales deberian ser")
            } catch (e: Exception) {
                throw Exception("**UNEXPECTED ERROR** $e")
            }
        }

        val porcentajeIncrementosAnios = 0.02
        val ciclosAniosIncremento = 5
    }

    private var metrosCuadrados: Int = 0
    private var valorContenido: Double = 0.0
    private var direccion: String = ""
    private var aniosConstruccion: Int = 0


    constructor(
        dniTitular: String,
        importe: Double,
        metrosCuadradosIntroducido: Int,
        valorContenidoIntroducido: Double,
        direccionIntroducido: String,
        aniosConstruccionIntroducido: Int
    ) : super(generarNumPoliza(), dniTitular, importe) {
        metrosCuadrados = metrosCuadradosIntroducido
        valorContenido = valorContenidoIntroducido
        direccion = direccionIntroducido
        aniosConstruccion = aniosConstruccionIntroducido
    }

    private constructor(
        numPoliza: Int,
        dniTitular: String,
        importe: Double,
        metrosCuadradosIntroducido: Int,
        valorContenidoIntroducido: Double,
        direccionIntroducido: String,
        aniosConstruccionIntroducido: Int
    ): super(numPoliza, dniTitular, importe) {
        metrosCuadrados = metrosCuadradosIntroducido
        valorContenido = valorContenidoIntroducido
        direccion = direccionIntroducido
        aniosConstruccion = aniosConstruccionIntroducido
    }

    override fun calcuarImporteAnioSiguiente(interes: Double): Double {
        var interes = interes/100
        while (aniosConstruccion >= ciclosAniosIncremento){
            interes += porcentajeIncrementosAnios
            aniosConstruccion -= ciclosAniosIncremento
        }
        return importe * (1.0 + interes)
    }

    override fun serializar(separador: String): String {
        return super.serializar(separador) + "$separador$metrosCuadrados$separador$valorContenido$separador$direccion$separador$aniosConstruccion"
    }

    override fun toString(): String {
        return super.toString() + ", metrosCuadrados=$metrosCuadrados, valorContenido=$valorContenido, direccion=$direccion, aniosConstruccion=$aniosConstruccion"
    }

}


