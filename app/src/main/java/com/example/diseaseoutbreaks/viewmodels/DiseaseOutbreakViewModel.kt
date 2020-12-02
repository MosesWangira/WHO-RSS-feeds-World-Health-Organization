package com.example.diseaseoutbreaks.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseDataClass
import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseItem
import com.example.diseaseoutbreaks.data.adapter.DiseasesAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class DiseaseOutbreakViewModel(application: Application) : AndroidViewModel(application) {
    /**
     *  LiveData gives us updated data when they change.
     * */
    private var allDiseases: MutableLiveData<DiseaseDataClass>

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

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
            val fetchingDiseases = RetrofitBuilder.apiService.getDiseasesAsync().await()
            allDiseases.postValue(fetchingDiseases)
        }catch (networkError: IOException){
            allDiseases.postValue(null)
        }
    }
}