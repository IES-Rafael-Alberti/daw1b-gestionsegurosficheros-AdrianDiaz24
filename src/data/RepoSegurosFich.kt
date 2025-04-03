package data

import model.Seguro
import model.SeguroAuto
import model.SeguroHogar
import model.SeguroVida

class RepoSegurosFich: ICargarSegurosIniciales {
    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean {
        TODO("Not yet implemented")
    }

    private fun actualizarContadores(seguros: List<Seguro>) {
        // Actualizar los contadores de polizas del companion object según el tipo de seguro
        val maxHogar = seguros.filter { it.tipoSeguro() == "SeguroHogar" }.maxOfOrNull { it.numPoliza }
        val maxAuto = seguros.filter { it.tipoSeguro() == "SeguroAuto" }.maxOfOrNull { it.numPoliza }
        val maxVida = seguros.filter { it.tipoSeguro() == "SeguroVida" }.maxOfOrNull { it.numPoliza }

        if (maxHogar != null) SeguroHogar.id = maxHogar
        if (maxAuto != null) SeguroAuto.id = maxAuto
        if (maxVida != null) SeguroVida.id = maxVida
    }

}