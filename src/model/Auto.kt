package model
enum class Auto {

    COCHE,
    Moto,
    CAMION;

    companion object{
        fun getAuto(valor: String): Auto {
            return when (valor.uppercase()){
                "MOTO" -> Moto
                "CAMION" -> CAMION
                else -> COCHE
            }
        }
    }

}