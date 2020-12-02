package com.example.diseaseoutbreaks.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.diseaseoutbreaks.data.Model.productalert.ProductAlertDataClass
import com.example.diseaseoutbreaks.data.Model.productalert.ProductItem
import com.example.diseaseoutbreaks.data.adapter.ProductAlertAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.await
import java.io.IOException

class ProductAlertViewModel(application: Application) : AndroidViewModel(application) {
    /**
     *  LiveData gives us updated data when they change.
     * */
    private var allMedicalProduct: MutableLiveData<ProductAlertDataClass>

    private var adapter: ProductAlertAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allMedicalProduct = MutableLiveData()
        adapter = ProductAlertAdapter()
        fetchProductInCoroutine()
    }

    fun getMyAdapter(): ProductAlertAdapter {
        return adapter
    }

    fun setAdapterData(data: List<ProductItem>) {
        adapter.apply {
            setDataList(data)
            notifyDataSetChanged()
        }
    }

    fun getAllMedicalProducts(): MutableLiveData<ProductAlertDataClass> {
        return allMedicalProduct
    }

    private fun fetchProductInCoroutine() = viewModelScope.launch {
        try{
            val fetchingProduct = RetrofitBuilder.apiService.getMedicalProductNewsAsync().await()
            allMedicalProduct.postValue(fetchingProduct)
        }catch (networkError: IOException){
            allMedicalProduct.postValue(null)
        }
    }
}