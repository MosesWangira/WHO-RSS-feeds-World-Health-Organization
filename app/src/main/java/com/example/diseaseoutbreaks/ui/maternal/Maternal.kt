package com.example.diseaseoutbreaks.ui.maternal

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
import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseDataClass
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalDataClass
import com.example.diseaseoutbreaks.databinding.FragmentMaternalBinding
import com.example.diseaseoutbreaks.functions.toast
import com.example.diseaseoutbreaks.ui.diseaseOutbreak.DiseaseOutbreakViewModel
import com.example.diseaseoutbreaks.ui.news.NewsViewModel

class Maternal : Fragment() {

    lateinit var binding: FragmentMaternalBinding

    lateinit var viewModel: MaternalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maternal, container, false)

        Log.d("Disease: " ,"Disease ViewModel Called")
        @Suppress("DEPRECATION")
        viewModel = ViewModelProviders.of(this).get(MaternalViewModel::class.java)

        binding.fragmentMaternalViewModel = viewModel
        binding.lifecycleOwner = this

        makeApiCall()


        return binding.root
    }

    private fun makeApiCall(): MaternalViewModel {
        @Suppress("DEPRECATION")
        viewModel.getAllMaternalHealth().observe(this, Observer<MaternalDataClass> {
            if (it != null) {
                /**
                 * update the adapter
                 * */
                /**
                 * update the adapter
                 * */
                binding.maternalRecyclerView.hasFixedSize()
                val resId: Int = R.anim.layout_animation_fall_down
                val animation: LayoutAnimationController =
                    AnimationUtils.loadLayoutAnimation(requireContext(), resId)
                binding.maternalRecyclerView.layoutAnimation = animation
                viewModel.setAdapterData(it.items)

            } else {
                requireContext().toast("Error Fetching data")
            }
        })
        viewModel.fetchMaternalInformation()

        return viewModel
    }
}