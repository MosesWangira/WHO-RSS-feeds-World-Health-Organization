package com.example.diseaseoutbreaks.ui.emergency

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyDataClass
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyItem
import com.example.diseaseoutbreaks.data.adapter.EmergencyAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response

class EmergencyViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated words when they change.
     * */
    var allEmergencies: MutableLiveData<EmergencyDataClass>

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

    fun fetchEmergencyData() {
        val fetchingDiseases = RetrofitBuilder.apiService.getEmergency()
        fetchingDiseases.enqueue(object : retrofit2.Callback<EmergencyDataClass> {
            override fun onFailure(call: Call<EmergencyDataClass>, t: Throwable) {
                allEmergencies.postValue(null)
            }

            override fun onResponse(
                call: Call<EmergencyDataClass>,
                response: Response<EmergencyDataClass>
            ) {
                if (response.isSuccessful) {
                    allEmergencies.postValue(response.body())
                } else {
                    allEmergencies.postValue(null)
                }
            }

        })
    }
}