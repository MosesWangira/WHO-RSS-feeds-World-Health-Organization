package com.example.diseaseoutbreaks.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.news.NewsDataClass
import com.example.diseaseoutbreaks.databinding.FragmentNewsBinding
import com.example.diseaseoutbreaks.util.animate
import com.example.diseaseoutbreaks.util.toast
import com.example.diseaseoutbreaks.viewmodels.NewsViewModel

class News : Fragment() {

    lateinit var binding: FragmentNewsBinding

    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        Log.d("Disease: ", "Disease ViewModel Called")
        @Suppress("DEPRECATION")
        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        binding.fragmentNewsViewModel = viewModel
        binding.lifecycleOwner = this

        makeApiCallCoroutine()
        return binding.root
    }

    private fun makeApiCallCoroutine(): NewsViewModel {
        @Suppress("DEPRECATION")
        viewModel.getAllNewsData().observe(this, Observer<NewsDataClass> {
            if (it != null) {
                /**
                 * update the adapter
                 * */

                binding.newsRecyclerView.apply {
                    hasFixedSize()
                    layoutAnimation = animate(requireContext(), R.anim.layout_animation_slide_right)
                }

                viewModel.setAdapterData(it.items)

            } else {
                requireContext().toast("Error retrieving data")
            }
        })

        return viewModel
    }
}