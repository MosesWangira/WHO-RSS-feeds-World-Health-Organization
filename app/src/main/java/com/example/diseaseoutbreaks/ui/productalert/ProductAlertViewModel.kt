package com.example.diseaseoutbreaks.ui.productalert

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.diseaseoutbreaks.data.Model.productalert.ProductAlertDataClass
import com.example.diseaseoutbreaks.data.Model.productalert.ProductItem
import com.example.diseaseoutbreaks.data.adapter.ProductAlertAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response

class ProductAlertViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated words when they change.
     * */
    var allMedicalProduct: MutableLiveData<ProductAlertDataClass>

    private var adapter: ProductAlertAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allMedicalProduct = MutableLiveData()
        adapter = ProductAlertAdapter()
    }

    fun getMyAdapter(): ProductAlertAdapter {
        return adapter
    }

    fun setAdapterData(data: List<ProductItem>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }

    fun getAllMedicalProducts(): MutableLiveData<ProductAlertDataClass> {
        return allMedicalProduct
    }

    fun fetchMedicalProducts() {
        val fetchingDiseases = RetrofitBuilder.apiService.getMedicalProductNews()
        fetchingDiseases.enqueue(object : retrofit2.Callback<ProductAlertDataClass> {
            override fun onFailure(call: Call<ProductAlertDataClass>, t: Throwable) {
                allMedicalProduct.postValue(null)
            }

            override fun onResponse(
                call: Call<ProductAlertDataClass>,
                response: Response<ProductAlertDataClass>
            ) {
                if (response.isSuccessful) {
                    allMedicalProduct.postValue(response.body())
                } else {
                    allMedicalProduct.postValue(null)
                }
            }

        })
    }
}