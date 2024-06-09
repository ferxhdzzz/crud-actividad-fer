package RecyclerViewHelpers

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fernanda.hernandez.crudfernadaactividad.R
import fernanda.hernandez.crudfernadaactividad.detallehelpdesk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import modelo.claseconexion
import modelo.tbticket
import java.util.UUID


class Adaptador (var Datos: List<tbticket>): RecyclerView.Adapter<ViewHolder>() {

    fun actualizarlista (nuevoList: List<tbticket>) {
        Datos = nuevoList
        notifyDataSetChanged()  //notifica al recycle view  que hay datos nuevos


    }
    fun actualizarpantalla (numeroticket: String, nuevonombre: String){
        val index =Datos.indexOfFirst{it.txtnumero == numeroticket}
        Datos[index].txttitulo = nuevonombre
        notifyItemChanged(index)
    }

    fun eliminardatos(numeroticket: String, position: Int) {

        //actualizar lista
        val listadatos = Datos.toMutableList()
        listadatos.removeAt(position)

        GlobalScope.launch(Dispatchers.IO) {

            //objeto clase conexion
            val objconexion = claseconexion().cadenaconexion()

            //crear una variable que contenga un preparestatement = manda la info a sql
            val deleteticket =
                objconexion?.prepareStatement("delete from ticket where numeroticket = ? ")!!
            deleteticket.setString(1,numeroticket)
            deleteticket.executeUpdate()

            val commit = objconexion?.prepareStatement("commit")!!
            commit.executeUpdate()


        }
        Datos = listadatos.toList()
        //notificar que los datos se cambiaron
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    fun actualizardatos(nuevonombre: String, numeroticket: String){
        GlobalScope.launch(Dispatchers.IO) {
            val objConexion = claseconexion().cadenaconexion()

            //VAIRABLE QUE TENGA UN PREPARESTATEMENT
            val updateticket = objConexion?.prepareStatement("update ticket set numeroticket = ? where numeroticket = ?")!!
            updateticket.setString(1, nuevonombre)
            updateticket.setString(2, numeroticket)
            updateticket.executeUpdate()

            val commit = objConexion.prepareStatement("commit")!!
            commit.executeUpdate()

            withContext(Dispatchers.Main){
                actualizarpantalla( nuevonombre, numeroticket)


            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val vista = LayoutInflater.from(parent.context).inflate(R.layout.activity_item_card, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val item = Datos [position]
        holder.txtcard.text = item.txtnumero

        holder.imgborrar.setOnClickListener {
            val context = holder.itemView.context
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Eliminar")
            builder.setMessage("¿Desea eliminar el ticket?")

            //botones
            builder.setPositiveButton("si") { dialog, which ->
                eliminardatos(item.txtnumero, position)


            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }

        //alerta para editar
        holder.imgeditar.setOnClickListener {
            val context = holder.itemView.context
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Editar")
            builder.setMessage("¿Desea editar el ticket?")

            //cuadro de texto para editar
            val cuadrotexto = EditText(context)
            cuadrotexto.setHint(item.txtnumero)
            builder.setView(cuadrotexto)

            //botones
            builder.setPositiveButton("si") { dialog, which ->
                actualizardatos(cuadrotexto.text.toString(), item.txtnumero)
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
        }
        holder.itemView.setOnClickListener(){
            val context = holder.itemView.context

            //cambiar pantalla
            val pantalladetalle = Intent(context, detallehelpdesk::class.java)

            pantalladetalle.putExtra("numeroticket", item.txtnumero)
            pantalladetalle.putExtra("tituloticket", item.txttitulo)
            pantalladetalle.putExtra("descripcion", item.txtdesc)
            pantalladetalle.putExtra("autor", item.txtautor)
            pantalladetalle.putExtra("email", item.txtemail)
            pantalladetalle.putExtra("fechacreacion", item.txtfechacracion)
            pantalladetalle.putExtra("estado", item.txtestado)
            pantalladetalle.putExtra("fechafinalizacion", item.txtfechafinal)

            context.startActivity(pantalladetalle)
        }

    }

}