import java.math.RoundingMode


fun main(){

}

fun Double.redondear(decimales: Int): Double{
    return this.toBigDecimal().setScale(decimales, RoundingMode.HALF_UP).toDouble()
}