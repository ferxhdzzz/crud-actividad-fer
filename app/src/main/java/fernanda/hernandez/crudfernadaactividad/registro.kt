package fernanda.hernandez.crudfernadaactividad

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.claseconexion
import java.util.UUID

class registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtusuario = findViewById<TextView>(R.id.txtusuario)
        val txtcontra = findViewById<TextView>(R.id.txtcontra)
        val btnregistrarse = findViewById<Button>(R.id.btnregistrarsee)


        btnregistrarse.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                val objconexion = claseconexion().cadenaconexion()
                val addusuario = objconexion?.prepareStatement("insert into usuariosss (UUID, nombre, contraseña) values(?, ?, ?)")!!
                addusuario.setString(1, UUID.randomUUID().toString())
                addusuario.setString(2,txtusuario.text.toString())
                addusuario.setString(3,txtcontra.text.toString())
                addusuario.executeUpdate()
                withContext(Dispatchers.Main){
                    val login = Intent(this@registro, MainActivity::class.java)
                    startActivity(login)
                }
            }
        }
    }
}