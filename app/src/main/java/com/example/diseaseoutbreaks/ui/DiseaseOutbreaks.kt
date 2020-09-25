package com.example.diseaseoutbreaks.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.DataClass
import com.example.diseaseoutbreaks.data.adapter.DiseasesAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import com.example.diseaseoutbreaks.databinding.FragmentDiseaseOutbreaksBinding
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiseaseOutbreaks : Fragment() {

    lateinit var adapter: DiseasesAdapter

    lateinit var binding: FragmentDiseaseOutbreaksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_disease_outbreaks, container, false)
        fetchDiseases()
        return binding.root
    }

    private fun fetchDiseases() {
        val fetchingDiseases = RetrofitBuilder.apiService.getDiseases()
        fetchingDiseases.enqueue(object : Callback<DataClass> {
            override fun onFailure(call: Call<DataClass>, t: Throwable) {
                Toast.makeText(requireContext(), "No internet", Toast.LENGTH_LONG).show()
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
        recycler_view.hasFixedSize()
        adapter = DiseasesAdapter(items)
        recycler_view.adapter = adapter
    }

}