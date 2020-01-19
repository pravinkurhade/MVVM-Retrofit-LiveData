package com.example.codingassignment

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.codingassignment.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.continents.observe(this, Observer {
            val arrayList = ArrayList<String>()
            for (item in it) {
                arrayList.add(item.region)
            }
            val array = arrayList.distinct()
            val adapter = CustomAdapter(array)
            recycler_view.adapter = adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    class CustomAdapter(private val userList: List<String>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        //this method is returning the view for each item in the list
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_layout_continents, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItems(userList[position])
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, CountriesActivity::class.java)
                intent.putExtra("continent", userList[position])
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return userList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItems(user: String) {
                val textViewName = itemView.findViewById(R.id.name) as TextView
                textViewName.text = user.toUpperCase()
            }
        }
    }
}
