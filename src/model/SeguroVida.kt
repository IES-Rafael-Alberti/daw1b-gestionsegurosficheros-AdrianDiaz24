package model

import java.sql.Time
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SeguroVida: Seguro{

     companion object {
        var id = 80000

        fun generarNumPoliza(): Int{
            return id++
        }

         fun crearSeguroVida(datos: List<String>): SeguroVida{
             if (datos.size < 5) {throw IllegalArgumentException("**ERROR** Debes pasar una lista de String con los siguientes datos en este orden DNI del titular, Importe, Fecha de nacimiento, Nivel de riesgo y Indenizacion")}
             require(datos[2].matches(Regex("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}\$"))) { throw IllegalArgumentException("La fecha no sigue el siguiente formato DD/MM/YYYY")}
             try {
                 return SeguroVida(datos[0], datos[1].toDouble(), datos[2], Riesgo.getRiesgo(datos[3]), datos[4].toDouble())
             } catch (e: IllegalArgumentException) {
                 throw IllegalArgumentException("**ERROR** Argumentos intoducidos no coincinden con los cuales deberian ser")
             } catch (e: Exception) {
                 throw Exception("**UNEXPECTED ERROR** $e")
             }

         }
    }

    private var fechaNac = ""
    private var nivelRiesgo = Riesgo.BAJO
    private var indemnizacion = 0.0


    constructor(dniTitular: String, importe: Double, fechanacIntroducido: String, nivelRiesgoIntroducido: Riesgo, indemnizacionIntroducida: Double): super(generarNumPoliza(), dniTitular, importe){
        fechaNac = fechanacIntroducido
        nivelRiesgo = nivelRiesgoIntroducido
        indemnizacion = indemnizacionIntroducida
    }

    private constructor(numPoliza: Int, dniTitular: String, importe: Double, fechaNacIntroducido: String, nivelRiesgoIntroducido: Riesgo, indemnizacionIntroducida: Double): super(numPoliza, dniTitular, importe){
        fechaNac = fechaNacIntroducido
        nivelRiesgo = nivelRiesgoIntroducido
        indemnizacion = indemnizacionIntroducida
    }


    override fun calcuarImporteAnioSiguiente(interes: Double): Double {
        return when(nivelRiesgo) {
            Riesgo.BAJO -> importe * (1.02 + (interes/100))
            Riesgo.MEDIO -> importe * (1.05 + (interes/100))
            Riesgo.ALTO -> importe * (1.10 + (interes/100))
        }
    }

    fun calcularAños(): Int{
        val fechaActual =  LocalDate.now().format(DateTimeFormatter.ofPattern("DD/MM/YYYY")).toString().split("/")
        val fechaNacDividida = fechaNac.split("/")

        var años = (fechaActual[2].toInt() - fechaNacDividida[2].toInt()) - 1
        if (fechaActual[1].toInt() > fechaNacDividida[1].toInt()) {
            años + 1
        } else if (fechaActual[0].toInt() >=  fechaNacDividida[0].toInt()){
                años +1
        }
        return  años

    }

}