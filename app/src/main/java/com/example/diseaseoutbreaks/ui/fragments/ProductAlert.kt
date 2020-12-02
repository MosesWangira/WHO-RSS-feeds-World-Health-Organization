package com.example.diseaseoutbreaks.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.productalert.ProductAlertDataClass
import com.example.diseaseoutbreaks.databinding.FragmentProductAlertBinding
import com.example.diseaseoutbreaks.util.animate
import com.example.diseaseoutbreaks.util.hideLoadingProgress
import com.example.diseaseoutbreaks.util.rotateAndFadeIn
import com.example.diseaseoutbreaks.util.toast
import com.example.diseaseoutbreaks.viewmodels.ProductAlertViewModel


class ProductAlert : Fragment(R.layout.fragment_product_alert) {

    private lateinit var binding: FragmentProductAlertBinding

    private lateinit var viewModel: ProductAlertViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        viewModel = ViewModelProvider(this).get(ProductAlertViewModel::class.java)
        binding.apply {
            fragmentProductViewModel = viewModel
            lifecycleOwner = this@ProductAlert
            emptyView.visibility = View.GONE
            loading.apply {
                visibility = View.VISIBLE
                startAnimation(rotateAndFadeIn(requireContext(), R.anim.rotate_animation))
            }
        }
        makeApiCallInCoroutines()
    }

    private fun makeApiCallInCoroutines(): ProductAlertViewModel {
        viewModel.getAllMedicalProducts().observe(viewLifecycleOwner, Observer<ProductAlertDataClass> {
            if (it != null) {
                binding.emptyView.visibility = View.GONE
                hideLoadingProgress(binding.loading)
                /**
                 * update the adapter
                 * */
                binding.productRecyclerView.apply {
                    hasFixedSize()
                    layoutAnimation = animate(requireContext(), R.anim.layout_animation_slide_right)
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