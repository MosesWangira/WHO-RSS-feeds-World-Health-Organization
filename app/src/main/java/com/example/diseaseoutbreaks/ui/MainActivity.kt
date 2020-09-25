package com.example.diseaseoutbreaks.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.DataClass
import com.example.diseaseoutbreaks.data.adapter.DiseasesAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    lateinit var adapter: DiseasesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchDiseases()
    }

    private fun fetchDiseases() {
        val fetchingDiseases = RetrofitBuilder.apiService.getDiseases()
        fetchingDiseases.enqueue(object : Callback<DataClass> {
            override fun onFailure(call: Call<DataClass>, t: Throwable) {
                Toast.makeText(this@MainActivity, "No internet", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
                if (response.isSuccessful) {
                    val diseases = response.body()

                    val dis = diseases?.items?.size
                    Log.d("Successful :", "$dis")

                    diseases?.let {
                        showDisease(it)

                    }
                } else {
                }
            }

        })
    }

    private fun showDisease(items: DataClass) {
        recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler_view.hasFixedSize()
        adapter = DiseasesAdapter(items)
        recycler_view.adapter = adapter
    }
}

