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
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import java.io.IOException

class EmergencyViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated words when they change.
     * */
    private var allEmergencies: MutableLiveData<EmergencyDataClass>

    private var adapter: EmergencyAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allEmergencies = MutableLiveData()
        adapter = EmergencyAdapter()
    }

    fun getMyAdapter(): EmergencyAdapter {
        return adapter
    }

    fun setAdapterData(data: List<EmergencyItem>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }

    fun getAllEmergenciesData(): MutableLiveData<EmergencyDataClass> {
        return allEmergencies
    }

    fun fetchEmergencyDataInCoroutine() = viewModelScope.launch {
        try{
            val fetchingEmergency = RetrofitBuilder.apiService.getEmergency().await()
            allEmergencies.postValue(fetchingEmergency)
        }catch (networkError: IOException){
            //show infinite loading spinner
            allEmergencies.postValue(null)
        }
    }
}