package model

class SeguroHogar(numPoliza: Int, dniTitular: String, private val importe: Double, metrosCuadrados: Int, valorContenido: Double, direccion: String) : Seguro(numPoliza, dniTitular, importe) {
    override fun tipoSeguro(): String {
        return "Seguro de Hogar"
    }


}