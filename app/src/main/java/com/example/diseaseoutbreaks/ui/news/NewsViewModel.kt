package com.example.diseaseoutbreaks.ui.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.diseaseoutbreaks.data.Model.news.NewsDataClass
import com.example.diseaseoutbreaks.data.Model.news.NewsItem
import com.example.diseaseoutbreaks.data.adapter.NewsAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Response

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    /**
     *  LiveData gives us updated words when they change.
     * */
    var allNews: MutableLiveData<NewsDataClass>

    private var adapter: NewsAdapter

    init {
        Log.d("Disease", "Disease ViewModel created")
        allNews = MutableLiveData()
        adapter = NewsAdapter()
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

    fun fetchNews() {
        val fetchingDiseases = RetrofitBuilder.apiService.getNews()
        fetchingDiseases.enqueue(object : retrofit2.Callback<NewsDataClass> {
            override fun onFailure(call: Call<NewsDataClass>, t: Throwable) {
                allNews.postValue(null)
            }

            override fun onResponse(call: Call<NewsDataClass>, response: Response<NewsDataClass>) {
                if (response.isSuccessful) {
                    allNews.postValue(response.body())
                } else {
                    allNews.postValue(null)
                }
            }

        })
    }
}