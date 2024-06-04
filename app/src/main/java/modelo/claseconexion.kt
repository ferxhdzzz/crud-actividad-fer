package modelo

import java.sql.Connection
import java.sql.DriverManager

class claseconexion {

    fun cadenaconexion() : Connection? {
        try {
            val url = "jdbc:oracle:thin:@10.10.1.168:1521:xe"
            val usuario = "SYSTEM"
            val contrasena = "desarrollo"

            val connection = DriverManager.getConnection(url, usuario, contrasena)
            return connection
        } catch (e: Exception) {
            println("error: $e")
            return null
        }
    }

}