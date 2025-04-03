package model

class Usuario(val nombre: String, clave: String, val perfil: Perfil): IExportable {

    companion object{

        val usuarios = mutableSetOf<String>()

        /**
         *  Crea una nueva instancia de Usuario
         *  @param datos Una lista de String con los siguientes datos en este orden nombre, clave, perfil
         */

        fun crearUsuario(datos: List<String>): Usuario{
            if (datos.size < 3) {throw IllegalArgumentException("**ERROR** Debes pasar una lista de String con los siguientes datos en este orden nombre, clave, perfil")}
            return Usuario(datos[0], datos[1], Perfil.getPerfil(datos[2]))
        }

    }

    init {
        require(usuarios.add(nombre)) {throw IllegalArgumentException("El nombre de usuario ya existe")}
    }

    var clave = clave
        private set

    override fun serializar(separador: String): String {
        return "$nombre$separador$clave$separador$perfil"
    }

    fun cambiarClave(nuevaClaveEncriptada: String){
        clave = nuevaClaveEncriptada
    }

}