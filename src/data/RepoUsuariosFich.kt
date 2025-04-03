package data

import utils.IUtilFicheros
import model.Usuario
import java.io.File

class RepoUsuariosFich(val rutaArchivo: String, val fich: IUtilFicheros): RepoUsuarioMem(), ICargarUsuariosIniciales {
    override fun cargarUsuarios(): Boolean {
        val archivo = File(rutaArchivo)

        if (archivo.exists() && archivo.isFile) {
            val listaString  = archivo.readLines()

            for (linea in listaString) {
                val datos = linea.split(";")

                try {
                    val usuario = Usuario.crearUsuario(datos)
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

    override fun eliminar(usuario: Usuario): Boolean {
        if (fich.escribirArchivo(rutaArchivo, usuarios.filter { it != usuario })) {
            return super.eliminar(usuario)
        }
        return false
    }

    override fun agregar(usuario: Usuario): Boolean {
        if (!super.agregar(usuario)){
            return false
        }
        return fich.agregarLinea(rutaArchivo, usuario.serializar())
    }

    override fun cambiarClave(usuario: Usuario, nuevaClave: String): Boolean {
        usuario.cambiarClave(nuevaClave)
        return fich.escribirArchivo(rutaArchivo, usuarios)
    }

}