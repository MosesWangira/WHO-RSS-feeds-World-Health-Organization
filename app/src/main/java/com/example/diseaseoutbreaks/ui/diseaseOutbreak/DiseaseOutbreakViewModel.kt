package com.example.diseaseoutbreaks.ui.diseaseOutbreak

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.diseaseoutbreaks.data.Model.DataClass
import com.example.diseaseoutbreaks.data.Model.Item
import com.example.diseaseoutbreaks.data.adapter.DiseasesAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import com.example.diseaseoutbreaks.functions.toast
import retrofit2.Call
import retrofit2.Response

class DiseaseOutbreakViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated words when they change.
     * */
    var allDiseases: MutableLiveData<DataClass>

    private var adapter: DiseasesAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allDiseases = MutableLiveData()
        adapter = DiseasesAdapter()
    }

    fun getMyAdapter(): DiseasesAdapter {
        return adapter
    }

    fun setAdapterData(data: List<Item>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }

    fun getAllDiseaseOutBreaks(): MutableLiveData<DataClass> {
        return allDiseases
    }

    fun fetchDiseases() {
        val fetchingDiseases = RetrofitBuilder.apiService.getDiseases()
        fetchingDiseases.enqueue(object : retrofit2.Callback<DataClass> {
            override fun onFailure(call: Call<DataClass>, t: Throwable) {
                allDiseases.postValue(null)
            }

            override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
                if (response.isSuccessful) {
                    allDiseases.postValue(response.body())
                } else {
                    allDiseases.postValue(null)
                }
            }

        })
    }
}