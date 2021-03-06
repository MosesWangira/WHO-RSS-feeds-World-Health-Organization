package com.example.diseaseoutbreaks.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyDataClass
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyItem
import com.example.diseaseoutbreaks.data.adapter.EmergencyAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class EmergencyViewModel(application: Application) : AndroidViewModel(application) {
    /**
     *  LiveData gives us updated data when they change.
     * */
    private var allEmergencies: MutableLiveData<EmergencyDataClass>

    private var adapter: EmergencyAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allEmergencies = MutableLiveData()
        adapter = EmergencyAdapter()
        fetchEmergencyDataInCoroutine()
    }

    fun getMyAdapter(): EmergencyAdapter {
        return adapter
    }

    fun setAdapterData(data: List<EmergencyItem>) {
        adapter.apply {
            setDataList(data)
            notifyDataSetChanged()
        }
    }

    fun getAllEmergenciesData(): MutableLiveData<EmergencyDataClass> {
        return allEmergencies
    }

    private fun fetchEmergencyDataInCoroutine() = viewModelScope.launch {
        try{
            val fetchingEmergency = RetrofitBuilder.apiService.getEmergencyAsync().await()
            allEmergencies.postValue(fetchingEmergency)
        }catch (networkError: IOException){
            allEmergencies.postValue(null)
        }
    }
}