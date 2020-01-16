package com.example.diseaseoutbreaks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diseaseoutbreaks.Model.DataClass
import com.example.diseaseoutbreaks.Model.Item
import com.example.diseaseoutbreaks.Model.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchDiseases()
    }

    private fun fetchDiseases() {
        val fetching_diseases =  RetrofitBuilder.apiService.getDiseases()

        fetching_diseases.enqueue(object : Callback<List<Item>>{

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                Log.d("Check error", t.message)
            }

            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful){
                    val diseases = response.body()

                    diseases?.let{
                        showDisease(it)

                    }
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun showDisease(items: List<Item>){
        recycle_view_display.layoutManager = LinearLayoutManager(this@MainActivity)
        recycle_view_display.hasFixedSize()
        recycle_view_display.adapter = DiseasesAdapter(items)
    }
}
