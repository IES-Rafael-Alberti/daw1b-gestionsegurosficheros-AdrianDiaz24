package data

import model.Perfil
import model.Usuario

open class RepoUsuarioMem: IRepoUsuarios {

    val usuarios = mutableListOf<Usuario>()

    override fun agregar(usuario: Usuario): Boolean {
        if (buscar(usuario.nombre) == null) {
            return false
        } else return usuarios.add(usuario)
    }

    override fun buscar(nombreUsuario: String): Usuario? {
        return usuarios.find { it.nombre == nombreUsuario }
    }

    override fun eliminar(usuario: Usuario): Boolean {
        return usuarios.remove(usuario)
    }

    override fun eliminar(nombreUsuario: String): Boolean {
        return eliminar(buscar(nombreUsuario)!!)
    }

    override fun obtenerTodos(): List<Usuario> {
        return usuarios.toList()
    }

    override fun obtener(perfil: Perfil): List<Usuario> {
        return usuarios.toList().filter { it.perfil == perfil }
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        usuario.cambiarClave(nuevaClave)
        return true
    }
}