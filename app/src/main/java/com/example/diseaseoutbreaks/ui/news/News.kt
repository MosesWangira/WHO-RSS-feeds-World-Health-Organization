package com.example.diseaseoutbreaks.ui.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.diseases.DataClass
import com.example.diseaseoutbreaks.data.Model.news.NewsDataClass
import com.example.diseaseoutbreaks.databinding.FragmentNewsBinding
import com.example.diseaseoutbreaks.functions.toast
import com.example.diseaseoutbreaks.ui.diseaseOutbreak.DiseaseOutbreakViewModel

class News : Fragment() {

    lateinit var binding: FragmentNewsBinding

    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        Log.d("Disease: " ,"Disease ViewModel Called")
        @Suppress("DEPRECATION")
        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        binding.fragmentNewsViewModel = viewModel
        binding.lifecycleOwner = this

        makeApiCall()

        return binding.root
    }

    private fun makeApiCall(): NewsViewModel {
        @Suppress("DEPRECATION")
        viewModel.getAllNewsData().observe(this, Observer<NewsDataClass> {
            if (it != null) {
                /**
                 * update the adapter
                 * */
                binding.newsRecyclerView.hasFixedSize()
                val resId: Int = R.anim.layout_animation_slide_right
                val animation: LayoutAnimationController =
                    AnimationUtils.loadLayoutAnimation(requireContext(), resId)
                binding.newsRecyclerView.layoutAnimation = animation
                viewModel.setAdapterData(it.items)

            } else {
                requireContext().toast("Error Fetching data")
            }
        })
        viewModel.fetchNews()

        return viewModel
    }
}