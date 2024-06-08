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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtnombre = findViewById<TextView>(R.id.txtnombre)
        val txtcontasena = findViewById<TextView>(R.id.txtcontrasena)
        val btnlogin = findViewById<Button>(R.id.btnlogin)
        val txtregistrarse = findViewById<TextView>(R.id.txtregistrarse)

        btnlogin.setOnClickListener{
            CoroutineScope(Dispatchers.Main).launch{
                val objconexion = claseconexion().cadenaconexion()
                val addusuario = objconexion?.prepareStatement("insert into usuariosss (UUID, nombre, contraseña) values(?, ?, ?)")!!
                addusuario.setString(1,UUID.randomUUID().toString())
                addusuario.setString(2,txtnombre.text.toString())
                addusuario.setString(3,txtcontasena.text.toString())
                addusuario.executeUpdate()
                withContext(Dispatchers.Main){
                    val helpdesk = Intent(this@MainActivity, helpdesk::class.java)
                    startActivity(helpdesk)
                }

            }

        }

    }


}
