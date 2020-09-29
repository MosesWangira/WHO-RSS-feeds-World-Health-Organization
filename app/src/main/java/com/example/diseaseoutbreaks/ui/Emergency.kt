package com.example.diseaseoutbreaks.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.emergency.EmergencyDataClass
import com.example.diseaseoutbreaks.databinding.FragmentEmergencyBinding
import com.example.diseaseoutbreaks.util.animate
import com.example.diseaseoutbreaks.util.hideLoadingProgress
import com.example.diseaseoutbreaks.util.rotateAndFadeIn
import com.example.diseaseoutbreaks.util.toast
import com.example.diseaseoutbreaks.viewmodels.EmergencyViewModel

class Emergency : Fragment(R.layout.fragment_emergency) {

    private lateinit var binding: FragmentEmergencyBinding

    private lateinit var viewModel: EmergencyViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DataBindingUtil.bind(view)!!
        viewModel = ViewModelProvider(this).get(EmergencyViewModel::class.java)
        binding.apply {
            fragmentEmergencyViewModel = viewModel
            lifecycleOwner = this@Emergency
            emptyView.visibility = View.GONE
            loading.apply {
                visibility = View.VISIBLE
                startAnimation(rotateAndFadeIn(requireContext(), R.anim.rotate_animation))
            }
        }
        makeApiCallInCoroutines()
    }

    private fun makeApiCallInCoroutines(): EmergencyViewModel {
        viewModel.getAllEmergenciesData().observe(viewLifecycleOwner, Observer<EmergencyDataClass> {
            if (it != null) {
                binding.emptyView.visibility = View.GONE
                hideLoadingProgress(binding.loading)
                /**
                 * update the adapter
                 * */
                binding.emergencyRecyclerView.apply {
                    hasFixedSize()
                    layoutAnimation = animate(requireContext(), R.anim.layout_animation_from_bottom)
                }

                viewModel.setAdapterData(it.items)

            } else {
                binding.emptyView.visibility = View.VISIBLE
                hideLoadingProgress(binding.loading)
                requireContext().toast("Click refresh icon to load data")
            }
        })

        return viewModel
    }

}