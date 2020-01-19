package com.example.codingassignment

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ahmadrosid.svgloader.SvgLoader
import com.example.codingassignment.model.Continents
import kotlinx.android.synthetic.main.activity_country_info.*
import kotlinx.android.synthetic.main.content_country_info.*

class CountryInfoActivity : AppCompatActivity() {

    lateinit var country: Continents

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_info)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var bundle: Bundle? = intent.extras
        if (bundle != null)
            country = bundle.getSerializable("country") as Continents

        SvgLoader.pluck()
            .with(this)
            .setPlaceHolder(R.drawable.placeholder, R.drawable.placeholder)
            .load(country.flag, imageView)

        textView_name.text = country.name
        textView_capital.text = country.capital
        textView_population.text = country.population.toString()
        textView_region.text = country.region
        textView_subregion.text = country.subregion
        textView_borders.text = country.borders.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
