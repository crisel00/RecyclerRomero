package com.cromero.recyclerromero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var listaGuitarras:MutableList<Guitarra> = mutableListOf<Guitarra>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = LinearLayoutManager(this)
        cargarGuitarras()
    }

    fun cargarGuitarras() {
        var listaGuitarras = ArrayList<Guitarra>()
        val url = "http://iesayala.ddns.net/json/jsonguitarras2.php"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            val jsonArray = JSONArray(response)
            for (i in 0..jsonArray.length()-1) {
                val jsonObject = JSONObject(jsonArray.getString(i));
                val guita = Guitarra(jsonObject.getString("marca"),jsonObject.getString("modelo"),"https://www.stockmusical.com/14261-thickbox_default/guitarra-electrica-st-5-rt-vision-strat.jpg")
                listaGuitarras.add(guita)
            }
            val adapter = AdaptadorGuitarras(listaGuitarras)
            recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            recycler.adapter=adapter
        }, Response.ErrorListener {
            Toast.makeText(this, "Error al cargar", Toast.LENGTH_SHORT).show()
        })
        queue.add(stringRequest)
    }


}