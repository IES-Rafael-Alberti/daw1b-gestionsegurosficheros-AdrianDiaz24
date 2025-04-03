package data

import model.*
import utils.IUtilFicheros
import java.io.File

class RepoSegurosFich(val rutaArchivo: String, val fich: IUtilFicheros): RepoSegurosMem(), ICargarSegurosIniciales {

    override fun cargarSeguros(mapa: Map<String, (List<String>) -> Seguro>): Boolean {
        val archivo = File(rutaArchivo)

        if (archivo.exists() && archivo.isFile) {
            val listaString  = archivo.readLines()

            for (linea in listaString) {
                val datos = linea.split(";")
                val tipoSeguro = datos.last()
                try {
                    val constructorSeguro = mapa[tipoSeguro] ?: return false
                    seguros.add(constructorSeguro(datos.dropLast(1)))
                } catch (e: IllegalArgumentException) {
                    println(e)
                } catch (e: Exception) {
                    println(e)
                }
            }
            return true
        }
        return false
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

    override fun agregar(seguro: Seguro): Boolean {
        if (!super.agregar(seguro)){
            return false
        } else return fich.agregarLinea(rutaArchivo, seguro.serializar())
    }

    override fun eliminar(seguro: Seguro): Boolean {
        if (fich.escribirArchivo(rutaArchivo, seguros.filter { it != seguro })) {
            return super.eliminar(seguro)
        }
        return false
    }

}