package model

class SeguroHogar(
    dniTitular: String,
    importe: Double,
    metrosCuadrados: Int,
    valorContenido: Double,
    direccion: String) : Seguro(generarNumPoliza(), dniTitular, importe) {

    companion object {
        var id = 10000
        fun generarNumPoliza(): Int{
            return id++
        }
    }

        override fun tipoSeguro(): String {
            return "Seguro de Hogar"
        }


    }


