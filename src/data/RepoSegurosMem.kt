package data

import model.Seguro

class RepoSegurosMem:IRepoSeguros {

    val seguros = mutableListOf<Seguro>()

    override fun agregar(seguro: Seguro): Boolean {
        return seguros.add(seguro)
    }

    override fun buscar(numPoliza: Int): Seguro? {
        return seguros.find { it.numPoliza == numPoliza }
    }

    override fun eliminar(seguro: Seguro): Boolean {
        return seguros.remove(seguro)
    }

    override fun eliminar(numPoliza: Int): Boolean {
        return seguros.remove(buscar(numPoliza))
    }

    override fun obtenerTodos(): List<Seguro> {
        return seguros.toList()
    }

    override fun obtener(tipoSeguro: String): List<Seguro> {
        return seguros.toList().filter { it.tipoSeguro() == tipoSeguro }
    }

}