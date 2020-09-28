package com.example.diseaseoutbreaks.ui.emergency

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
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyDataClass
import com.example.diseaseoutbreaks.databinding.FragmentEmergencyBinding
import com.example.diseaseoutbreaks.util.animate
import com.example.diseaseoutbreaks.util.toast
import com.example.diseaseoutbreaks.viewmodels.EmergencyViewModel

class Emergency : Fragment() {

    lateinit var binding: FragmentEmergencyBinding

    lateinit var viewModel: EmergencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_emergency, container, false)


        Log.d("Disease: ", "Disease ViewModel Called")
        @Suppress("DEPRECATION")
        viewModel = ViewModelProviders.of(this).get(EmergencyViewModel::class.java)

        binding.fragmentEmergencyViewModel = viewModel
        binding.lifecycleOwner = this

        makeApiCallInCoroutines()

        return binding.root
    }

    private fun makeApiCallInCoroutines(): EmergencyViewModel {
        @Suppress("DEPRECATION")
        viewModel.getAllEmergenciesData().observe(this, Observer<EmergencyDataClass> {
            if (it != null) {
                /**
                 * update the adapter
                 * */

                binding.emergencyRecyclerView.apply {
                    hasFixedSize()
                    layoutAnimation = animate(requireContext(), R.anim.layout_animation_from_bottom)
                }

                viewModel.setAdapterData(it.items)

            } else {
                requireContext().toast("Error Fetching data")
            }
        })

        return viewModel
    }

}