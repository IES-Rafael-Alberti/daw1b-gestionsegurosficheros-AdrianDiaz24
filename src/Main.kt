import model.SeguroVida
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun main(){
    println(calcularAños("2/4/2000"))
}

fun Double.redondear(decimales: Int): Double{
    return this.toBigDecimal().setScale(decimales, RoundingMode.HALF_UP).toDouble()
}


fun calcularAños(fechaNac: String): Int{
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