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
        adapter.apply {
            setDataList(data)
            notifyDataSetChanged()
        }
    }

    fun getAllNewsData(): MutableLiveData<NewsDataClass> {
        return allNews
    }

    private fun fetchNewsInCoroutine() = viewModelScope.launch {
        try{
            val fetchingNews = RetrofitBuilder.apiService.getNewsAsync().await()
            allNews.postValue(fetchingNews)
        }catch (networkError: IOException){
            allNews.postValue(null)
        }
    }
}