package com.example.diseaseoutbreaks.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseDataClass
import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseItem
import com.example.diseaseoutbreaks.data.adapter.DiseasesAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response

class DiseaseOutbreakViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated words when they change.
     * */
    var allDiseases: MutableLiveData<DiseaseDataClass>

    private var adapter: DiseasesAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allDiseases = MutableLiveData()
        adapter = DiseasesAdapter()
    }

    fun getMyAdapter(): DiseasesAdapter {
        return adapter
    }

    fun setAdapterData(data: List<DiseaseItem>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }

    fun getAllDiseaseOutBreaks(): MutableLiveData<DiseaseDataClass> {
        return allDiseases
    }

    fun fetchDiseases() {
        val fetchingDiseases = RetrofitBuilder.apiService.getDiseases()
        fetchingDiseases.enqueue(object : retrofit2.Callback<DiseaseDataClass> {
            override fun onFailure(call: Call<DiseaseDataClass>, t: Throwable) {
                allDiseases.postValue(null)
            }

            override fun onResponse(call: Call<DiseaseDataClass>, response: Response<DiseaseDataClass>) {
                if (response.isSuccessful) {
                    allDiseases.postValue(response.body())
                } else {
                    allDiseases.postValue(null)
                }
            }

        })
    }
}