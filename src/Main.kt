import model.SeguroVida
import java.math.RoundingMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun main(){

}

fun Double.redondear(decimales: Int): Double{
    return this.toBigDecimal().setScale(decimales, RoundingMode.HALF_UP).toDouble()
}

