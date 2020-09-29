package com.example.diseaseoutbreaks.ui

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.diseases.DiseaseDataClass
import com.example.diseaseoutbreaks.databinding.FragmentDiseaseOutbreaksBinding
import com.example.diseaseoutbreaks.util.animate
import com.example.diseaseoutbreaks.util.rotateAndFadeIn
import com.example.diseaseoutbreaks.util.toast
import com.example.diseaseoutbreaks.viewmodels.DiseaseOutbreakViewModel


class DiseaseOutbreaks : Fragment(R.layout.fragment_disease_outbreaks) {

    private lateinit var viewModel: DiseaseOutbreakViewModel

    private lateinit var binding: FragmentDiseaseOutbreaksBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        viewModel = ViewModelProvider(this).get(DiseaseOutbreakViewModel::class.java)
        binding.apply {
            fragmentDiseaseViewModel = viewModel
            lifecycleOwner = this@DiseaseOutbreaks
            emptyView.visibility = View.GONE
            loading.apply {
                visibility = View.VISIBLE
                startAnimation(rotateAndFadeIn(requireContext(), R.anim.rotate_animation))
            }
        }
        makeApiCallCoroutines()
    }

    private fun makeApiCallCoroutines(): DiseaseOutbreakViewModel {
        viewModel.getAllDiseaseOutBreaks().observe(viewLifecycleOwner, Observer<DiseaseDataClass> {
            if (it != null) {
                binding.apply {
                    emptyView.visibility = View.GONE
                    hideLoadingProgress()
                }
                /**
                 * update the adapter
                 * */
                binding.recyclerView.apply {
                    hasFixedSize()
                    layoutAnimation = animate(requireContext(), R.anim.layout_animation_fall_down)
                }

                viewModel.setAdapterData(it.items)

            } else {
                binding.apply {
                    emptyView.visibility = View.VISIBLE
                    hideLoadingProgress()
                }

                requireContext().toast("Click refresh icon to load data")
            }
        })

        return viewModel
    }

    private fun hideLoadingProgress() {
        binding.loading.apply {
            clearAnimation()
            visibility = View.GONE
        }
    }
}