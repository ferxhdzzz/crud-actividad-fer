package fernanda.hernandez.crudfernadaactividad

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detallehelpdesk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detallehelpdesk)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //recibir valores de adpatador
        val numero = intent.getStringExtra("numeroticket")
        val nombrenuevo= intent.getStringExtra("nombreticket")
        val desc = intent.getStringExtra("descripcion")
        val autor = intent.getStringExtra("autor")
        val email = intent.getStringExtra("email")
        val fechacracion= intent.getStringExtra("fechacreacion")
        val estado = intent.getStringExtra("estado")
        val fechafinal= intent.getStringExtra("fechafinalizacion")

        //mandae elementos a la pantalla

        val txtnumero = findViewById<TextView>(R.id.numerodetalle)
        val txttitulo = findViewById<TextView>(R.id.nombredetalle)
        val txtdesc = findViewById<TextView>(R.id.descripciondetalle)
        val txtautor = findViewById<TextView>(R.id.autordetalle)
        val txtemail = findViewById<TextView>(R.id.emaildetalle)
        val txtfechacracion = findViewById<TextView>(R.id.fechacreaciondetalle)
        val txtestado = findViewById<TextView>(R.id.estadodetalle)
        val txtfechafinal = findViewById<TextView>(R.id.fechafinaldetalle)

        txtnumero.text = numero
        txttitulo.text = nombrenuevo
        txtdesc.text= desc
        txtautor.text = autor
        txtemail.text = email
        txtfechacracion.text = fechacracion
        txtestado.text = estado
        txtfechafinal.text = fechafinal

    }

}