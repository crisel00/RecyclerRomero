package com.cromero.recyclerromero

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdaptadorGuitarras(val listaGuitarras: ArrayList<Guitarra>) :
    RecyclerView.Adapter<AdaptadorGuitarras.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorGuitarras.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AdaptadorGuitarras.ViewHolder, position: Int) {
        holder.bindItems(listaGuitarras[position])
    }

    override fun getItemCount(): Int {
        return listaGuitarras.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(guitarras: Guitarra) {
            val textView:TextView = itemView.findViewById(R.id.texto)

            val imagen = itemView.findViewById(R.id.imagen) as ImageView
            textView.text = guitarras.marca.toString()+" "+guitarras.modelo
            Glide.with(itemView).load(guitarras.url).into(imagen)
        }
    }

}