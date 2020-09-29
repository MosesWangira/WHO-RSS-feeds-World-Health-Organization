package com.example.diseaseoutbreaks.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalDataClass
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalItem
import com.example.diseaseoutbreaks.data.adapter.MaternalAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response

class MaternalViewModel(application: Application) : AndroidViewModel(application) {
    /**
     *  LiveData gives us updated data when they change.
     * */
    var allMaternalHealthInformation: MutableLiveData<MaternalDataClass>

    private var adapter: MaternalAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allMaternalHealthInformation = MutableLiveData()
        adapter = MaternalAdapter()
        fetchMaternalInformation()
    }

    fun getMyAdapter(): MaternalAdapter {
        return adapter
    }

    fun setAdapterData(data: List<MaternalItem>) {
        adapter.apply {
            setDataList(data)
            notifyDataSetChanged()
        }
    }

    fun getAllMaternalHealth(): MutableLiveData<MaternalDataClass> {
        return allMaternalHealthInformation
    }

    private fun fetchMaternalInformation() {
        val fetchingDiseases = RetrofitBuilder.apiService.getMaternalInformationAsync()
        fetchingDiseases.enqueue(object : retrofit2.Callback<MaternalDataClass> {
            override fun onFailure(call: Call<MaternalDataClass>, t: Throwable) {
                allMaternalHealthInformation.postValue(null)
            }

            override fun onResponse(
                call: Call<MaternalDataClass>,
                response: Response<MaternalDataClass>
            ) {
                if (response.isSuccessful) {
                    allMaternalHealthInformation.postValue(response.body())
                } else {
                    allMaternalHealthInformation.postValue(null)
                }
            }

        })
    }
}