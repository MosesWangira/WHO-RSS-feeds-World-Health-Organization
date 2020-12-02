package com.example.diseaseoutbreaks.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalDataClass
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalItem
import com.example.diseaseoutbreaks.data.adapter.MaternalAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

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

    private fun fetchMaternalInformation() = viewModelScope.launch {
        try {
            val fetchingMaternal =
                RetrofitBuilder.apiService.getMaternalInformationAsync().await()
            allMaternalHealthInformation.postValue(fetchingMaternal)
        } catch (networkError: IOException) {
            allMaternalHealthInformation.postValue(null)
        }
    }
}