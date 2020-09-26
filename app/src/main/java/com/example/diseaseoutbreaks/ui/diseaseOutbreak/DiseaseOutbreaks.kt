package com.example.diseaseoutbreaks.ui.diseaseOutbreak

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.DataClass
import com.example.diseaseoutbreaks.databinding.FragmentDiseaseOutbreaksBinding
import com.example.diseaseoutbreaks.functions.toast


class DiseaseOutbreaks : Fragment() {

    private lateinit var viewModel: DiseaseOutbreakViewModel

    lateinit var binding: FragmentDiseaseOutbreaksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_disease_outbreaks, container, false)

        Log.d("Disease: " ,"Disease ViewModel Called")
        @Suppress("DEPRECATION")
        viewModel = ViewModelProviders.of(this).get(DiseaseOutbreakViewModel::class.java)

        binding.fragmentDiseaseViewModel = viewModel
        binding.lifecycleOwner = this

        makeApiCall()

        return binding.root
    }

    private fun makeApiCall(): DiseaseOutbreakViewModel {
        @Suppress("DEPRECATION")
        viewModel.getAllDiseaseOutBreaks().observe(this, Observer<DataClass> {
            if (it != null) {
                /**
                 * update the adapter
                 * */
                binding.recyclerView.hasFixedSize()
                val resId: Int = R.anim.layout_animation_fall_down
                val animation: LayoutAnimationController =
                    AnimationUtils.loadLayoutAnimation(requireContext(), resId)
                binding.recyclerView.layoutAnimation = animation
                viewModel.setAdapterData(it.items)

            } else {
                requireContext().toast("Error Fetching data")
            }
        })
        viewModel.fetchDiseases()

        return viewModel
    }
}