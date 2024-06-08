package modelo

import java.sql.Connection
import java.sql.DriverManager

class claseconexion {

    fun cadenaconexion() : Connection? {
        try {
            val url = "jdbc:oracle:thin:@192.168.1.14:1521:xe"
            val usuario = "itzfer_DEVELOPER"
            val contrasena = "huhyunjinmybeloved"

            val connection = DriverManager.getConnection(url, usuario, contrasena)
            return connection
        } catch (e: Exception) {
            println("error: $e")
            return null
        }
    }

}