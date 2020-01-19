package com.example.codingassignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.example.codingassignment.model.Continents
import com.example.codingassignment.viewModel.CountriesViewModel
import kotlinx.android.synthetic.main.activity_countries.*
import kotlinx.android.synthetic.main.content_countries.*


class CountriesActivity : AppCompatActivity() {

    private lateinit var viewModel: CountriesViewModel
    private lateinit var continent: String
    private var sortedList = listOf<Continents>()
    private var adapter: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var bundle: Bundle? = intent.extras
        if (bundle != null)
            continent = bundle.getString("continent")!!

        supportActionBar?.title = continent.toUpperCase()

        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)

        viewModel.setUserId(continent)

        initialize()

        viewModel.continents.observe(this, Observer {
            sortedList = it.sortedWith(compareBy { it.borders.size })
            updateData()
        })
    }

    private fun updateData() {
        Log.d("CountriesActivity", sortedList.toString())
        adapter = CustomAdapter(sortedList, this)
        recycler_view_country.adapter = adapter
    }

    private fun initialize() {
        val layoutManager = LinearLayoutManager(this)
        recycler_view_country.layoutManager = layoutManager
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.countris_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        if (item.itemId == R.id.action_asc) {
            sortedList = sortedList.sortedBy { it.borders.size }
            updateData()
        }
        if (item.itemId == R.id.action_desc) {
            sortedList = sortedList.sortedByDescending { it.borders.size }
            updateData()
        }
        return super.onOptionsItemSelected(item)
    }

    class CustomAdapter(
        private val userList: List<Continents>,
        private val countriesActivity: Activity
    ) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_layout_countries, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItems(userList[position], countriesActivity)
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, CountryInfoActivity::class.java)
                intent.putExtra("country", userList[position])
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return userList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bindItems(
                user: Continents,
                countriesActivity: Activity?
            ) {
                val textViewName = itemView.findViewById(R.id.textView_name) as TextView
                val textViewCapital = itemView.findViewById(R.id.textView_capital) as TextView
                val imageView = itemView.findViewById(R.id.imageView_flag) as ImageView
                textViewName.text = user.name
                textViewCapital.text = user.capital
                SvgLoader.pluck()
                    .with(countriesActivity)
                    .setPlaceHolder(R.drawable.placeholder, R.drawable.placeholder)
                    .load(user.flag, imageView)
            }
        }
    }
}
