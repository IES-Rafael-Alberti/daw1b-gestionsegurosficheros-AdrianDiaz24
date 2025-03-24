
enum class TipoAuto(val descripcion: String) {

    COCHE ("Coche"), Moto ("Moto"), CAMION ("Camion");

    override fun toString(): String {
        return descripcion
    }

}