package com.example.diseaseoutbreaks.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.diseaseoutbreaks.data.Model.news.NewsDataClass
import com.example.diseaseoutbreaks.data.Model.news.NewsItem
import com.example.diseaseoutbreaks.data.adapter.NewsAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import java.io.IOException

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated data when they change.
     * */
    private var allNews: MutableLiveData<NewsDataClass>

    private var adapter: NewsAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allNews = MutableLiveData()
        adapter = NewsAdapter()
        fetchNewsInCoroutine()
    }

    fun getMyAdapter(): NewsAdapter {
        return adapter
    }

    fun setAdapterData(data: List<NewsItem>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }

    fun getAllNewsData(): MutableLiveData<NewsDataClass> {
        return allNews
    }

    private fun fetchNewsInCoroutine() = viewModelScope.launch {
        try{
            val fetchingNews = RetrofitBuilder.apiService.getNews().await()
            allNews.postValue(fetchingNews)
        }catch (networkError: IOException){
            //show infinite loading spinner
            allNews.postValue(null)
        }
    }
}