package RecyclerViewHelpers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fernanda.hernandez.crudfernadaactividad.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtcard = view.findViewById<TextView>(R.id.txtticketcard)
    val imgeditar: ImageView = view.findViewById(R.id.imgeditar)
    val imgborrar: ImageView = view.findViewById(R.id.imgborrar)
}