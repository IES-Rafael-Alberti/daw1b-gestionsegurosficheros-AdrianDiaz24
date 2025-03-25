package model

enum class Perfil {
    ADMIN, GESTION, CONSULTA;

    companion object{
        fun getPerfil(valor: String): Perfil{
            return when (valor.uppercase()) {
                "GESTION" -> GESTION
                "ADMIN" -> ADMIN
                else -> CONSULTA
            }
        }
    }

}