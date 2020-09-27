package com.example.diseaseoutbreaks.ui.emergency

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
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyDataClass
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalDataClass
import com.example.diseaseoutbreaks.databinding.FragmentEmergencyBinding
import com.example.diseaseoutbreaks.databinding.FragmentMaternalBinding
import com.example.diseaseoutbreaks.functions.toast
import com.example.diseaseoutbreaks.ui.maternal.MaternalViewModel

class Emergency : Fragment() {

    lateinit var binding: FragmentEmergencyBinding

    lateinit var viewModel: EmergencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emergency, container, false)


        Log.d("Disease: " ,"Disease ViewModel Called")
        @Suppress("DEPRECATION")
        viewModel = ViewModelProviders.of(this).get(EmergencyViewModel::class.java)

        binding.fragmentEmergencyViewModel = viewModel
        binding.lifecycleOwner = this

        makeApiCall()

        return binding.root
    }

    private fun makeApiCall(): EmergencyViewModel {
        @Suppress("DEPRECATION")
        viewModel.getAllEmergenciesData().observe(this, Observer<EmergencyDataClass> {
            if (it != null) {
                /**
                 * update the adapter
                 * */

                binding.emergencyRecyclerView.hasFixedSize()
                val resId: Int = R.anim.layout_animation_slide_right
                val animation: LayoutAnimationController =
                    AnimationUtils.loadLayoutAnimation(requireContext(), resId)
                binding.emergencyRecyclerView.layoutAnimation = animation
                viewModel.setAdapterData(it.items)

            } else {
                requireContext().toast("Error Fetching data")
            }
        })
        viewModel.fetchEmergencyData()

        return viewModel
    }

}