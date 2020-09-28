package com.example.diseaseoutbreaks.ui.productalert

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.productalert.ProductAlertDataClass
import com.example.diseaseoutbreaks.databinding.FragmentProductAlertBinding
import com.example.diseaseoutbreaks.util.animate
import com.example.diseaseoutbreaks.util.toast
import com.example.diseaseoutbreaks.viewmodels.ProductAlertViewModel


class ProductAlert : Fragment( R.layout.fragment_product_alert) {

    lateinit var binding: FragmentProductAlertBinding

    lateinit var viewModel: ProductAlertViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        viewModel = ViewModelProvider(this).get(ProductAlertViewModel::class.java)
        binding.apply {
            fragmentProductViewModel = viewModel
            lifecycleOwner = this@ProductAlert
        }
        makeApiCallInCoroutines()
    }

    private fun makeApiCallInCoroutines(): ProductAlertViewModel {
        @Suppress("DEPRECATION")
        viewModel.getAllMedicalProducts().observe(this, Observer<ProductAlertDataClass> {
            if (it != null) {
                /**
                 * update the adapter
                 * */
                binding.productRecyclerView.apply {
                    hasFixedSize()
                    layoutAnimation = animate(requireContext(), R.anim.layout_animation_slide_right)
                }

                viewModel.setAdapterData(it.items)

            } else {
                requireContext().toast("Error Fetching data")
            }
        })

        return viewModel
    }
}