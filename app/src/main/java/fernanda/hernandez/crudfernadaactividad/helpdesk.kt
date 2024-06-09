package fernanda.hernandez.crudfernadaactividad

import RecyclerViewHelpers.Adaptador
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.claseconexion
import modelo.tbticket
import java.util.UUID

class helpdesk : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_helpdesk)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtnumero = findViewById<TextView>(R.id.txtnumero)
        val txttitulo = findViewById<TextView>(R.id.txttitulo)
        val txtdesc = findViewById<TextView>(R.id.txtdescripcion)
        val txtautor = findViewById<TextView>(R.id.txtautor)
        val txtemail = findViewById<TextView>(R.id.txtemail)
        val txtfechacracion = findViewById<TextView>(R.id.txtfechacreacion)
        val txtestado = findViewById<TextView>(R.id.txtestado)
        val txtfechafinal = findViewById<TextView>(R.id.txtfechafinalizacion)
        val btnagregar = findViewById<Button>(R.id.btnagregar)
        val rcbticket = findViewById<RecyclerView>(R.id.rcvticket)
        rcbticket.layoutManager = LinearLayoutManager(this)


        fun obtenerdatos(): List<tbticket>{


            val objConexion = claseconexion().cadenaconexion()


            val statement = objConexion?.createStatement()
            val resulSet = statement?.executeQuery("select * from ticket") !!
            val tickets = mutableListOf<tbticket>()

            //recorro todos los registros de la base de datos
            while (resulSet.next())  {
                val numero = resulSet.getString("numeroticket")
                val titulo = resulSet.getString("tituloticket")
                val desc = resulSet.getString("descripcion")
                val autor = resulSet.getString("autor")
                val email = resulSet.getString("email")
                val fechacracion = resulSet.getString("fechacreacion")
                val estado = resulSet.getString("estado")
                val fechafinalizacion = resulSet.getString("fechafinalizacion")

                val ticket = tbticket(numero, titulo, desc, autor, email, fechacracion, estado,fechafinalizacion)
                tickets.add(ticket)
            }
            return tickets


        }

        //Asignar el adaptador
        CoroutineScope(Dispatchers.IO).launch {
            val ticketbd = obtenerdatos()
            withContext(Dispatchers.Main){
                val adapter = Adaptador(ticketbd)
                rcbticket.adapter = adapter
            }
        }
        btnagregar.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch{
                val objconexion = claseconexion().cadenaconexion()
                val addticket = objconexion?.prepareStatement("insert into ticket (numeroticket, tituloticket, descripcion, autor, email, fechacreacion, estado, fechafinalizacion) values(?, ?, ?, ?, ?, ?, ?, ?)")!!
                addticket.setString(1, txtnumero.text.toString())
                addticket.setString(2,txttitulo.text.toString())
                addticket.setString(3,txtdesc.text.toString())
                addticket.setString(4, txtautor.text.toString())
                addticket.setString(5,txtemail.text.toString())
                addticket.setString(6,txtfechacracion.text.toString())
                addticket.setString(7, txtestado.text.toString())
                addticket.setString(8,txtfechafinal.text.toString())
                addticket.executeUpdate()

                val nuevoticket =  obtenerdatos()
                withContext(Dispatchers.Main) {
                    (rcbticket.adapter as? Adaptador)?.actualizarlista(nuevoticket)

                }
            }
        }
    }
}

