package data

interface IExportable {
    fun serializar(separador: String = ";"): String
}