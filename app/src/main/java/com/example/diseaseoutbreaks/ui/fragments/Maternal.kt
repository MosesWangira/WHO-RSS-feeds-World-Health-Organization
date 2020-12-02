package com.example.diseaseoutbreaks.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalDataClass
import com.example.diseaseoutbreaks.databinding.FragmentMaternalBinding
import com.example.diseaseoutbreaks.util.animate
import com.example.diseaseoutbreaks.util.hideLoadingProgress
import com.example.diseaseoutbreaks.util.rotateAndFadeIn
import com.example.diseaseoutbreaks.util.toast
import com.example.diseaseoutbreaks.viewmodels.MaternalViewModel

class Maternal : Fragment(R.layout.fragment_maternal) {

    private lateinit var binding: FragmentMaternalBinding

    private lateinit var viewModel: MaternalViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        viewModel = ViewModelProvider(this).get(MaternalViewModel::class.java)
        binding.apply {
            fragmentMaternalViewModel = viewModel
            lifecycleOwner = this@Maternal
            emptyView.visibility = View.GONE
            loading.apply {
                visibility = View.VISIBLE
                startAnimation(rotateAndFadeIn(requireContext(), R.anim.rotate_animation))
            }
        }

        makeApiCallInCoroutine()
    }

    private fun makeApiCallInCoroutine(): MaternalViewModel {
        viewModel.getAllMaternalHealth().observe(viewLifecycleOwner, Observer<MaternalDataClass> {
            if (it != null) {
                binding.emptyView.visibility = View.GONE
                hideLoadingProgress(binding.loading)
                /**
                 * update the adapter
                 * */
                binding.maternalRecyclerView.apply {
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