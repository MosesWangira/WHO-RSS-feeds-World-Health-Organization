package com.example.diseaseoutbreaks.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseDataClass
import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseItem
import com.example.diseaseoutbreaks.data.adapter.DiseasesAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import java.io.IOException

class DiseaseOutbreakViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated data when they change.
     * */
    private var allDiseases: MutableLiveData<DiseaseDataClass>

    private var adapter: DiseasesAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allDiseases = MutableLiveData()
        adapter = DiseasesAdapter()
        fetchDiseasesInCoroutine()
    }

    fun getMyAdapter(): DiseasesAdapter {
        return adapter
    }

    fun setAdapterData(data: List<DiseaseItem>) {
        adapter.apply {
            setDataList(data)
            notifyDataSetChanged()
        }
    }

    fun getAllDiseaseOutBreaks(): MutableLiveData<DiseaseDataClass> {
        return allDiseases
    }

    private fun fetchDiseasesInCoroutine() = viewModelScope.launch {
        try{
            val fetchingDiseases = RetrofitBuilder.apiService.getDiseases().await()
            allDiseases.postValue(fetchingDiseases)
        }catch (networkError: IOException){
            //show infinite loading spinner
            allDiseases.postValue(null)
        }
    }
}