package com.example.diseaseoutbreaks.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchDiseases()
    }

    private fun fetchDiseases() {
        val fetching_diseases = RetrofitBuilder.apiService.getDiseases()
        fetching_diseases.enqueue(object : Callback<DataClass> {
            override fun onFailure(call: Call<DataClass>, t: Throwable) {
                setContentView(R.layout.no_internet)

                val retry : Button = findViewById(R.id.retry_no_internet)
                retry.setOnClickListener {
                    fetchDiseases()
                }
//                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
//                Log.d("Check error", t.message)
            }

            override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
                if (response.isSuccessful) {
                    val diseases = response.body()

                    val dis = diseases?.items?.size
                    Log.d("Successful :", "${dis}")

                    diseases?.let {
                        showDisease(it)

                    }
//                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_LONG).show()
                } else {
//                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun showDisease(items: DataClass) {
        recycle_view_display.layoutManager = LinearLayoutManager(this@MainActivity)
        recycle_view_display.hasFixedSize()
        recycle_view_display.adapter =
            DiseasesAdapter(items)
    }

//    private fun checkNetworkConnectionStatus() {
//        val connMgr =
//            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeInfo = connMgr.activeNetworkInfo
//        if (activeInfo != null && activeInfo.isConnected)
//            {
//                //no internet connection
//                no_internet_image.setImageResource(R.drawable.ic_launcher_background)
//                no_internet_text.setText("No internet connection")
//                setContentView(R.layout.no_internet)
//            }
//        }
}
