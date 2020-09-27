package com.example.diseaseoutbreaks.ui.maternal

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

    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated words when they change.
     * */
    var allMaternalHealthInformation: MutableLiveData<MaternalDataClass>

    private var adapter: MaternalAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allMaternalHealthInformation = MutableLiveData()
        adapter = MaternalAdapter()
    }

    fun getMyAdapter(): MaternalAdapter {
        return adapter
    }

    fun setAdapterData(data: List<MaternalItem>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }

    fun getAllMaternalHealth(): MutableLiveData<MaternalDataClass> {
        return allMaternalHealthInformation
    }

    fun fetchMaternalInformation() {
        val fetchingDiseases = RetrofitBuilder.apiService.getMaternalInformation()
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