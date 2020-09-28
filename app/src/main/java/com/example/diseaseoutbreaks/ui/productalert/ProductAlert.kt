package com.example.diseaseoutbreaks.ui.productalert

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
import com.example.diseaseoutbreaks.data.Model.productalert.ProductAlertDataClass
import com.example.diseaseoutbreaks.databinding.FragmentProductAlertBinding
import com.example.diseaseoutbreaks.util.animate
import com.example.diseaseoutbreaks.util.toast
import com.example.diseaseoutbreaks.viewmodels.ProductAlertViewModel


class ProductAlert : Fragment() {

    lateinit var binding: FragmentProductAlertBinding

    lateinit var viewModel: ProductAlertViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_alert, container, false)

        Log.d("Disease: ", "Disease ViewModel Called")
        @Suppress("DEPRECATION")
        viewModel = ViewModelProviders.of(this).get(ProductAlertViewModel::class.java)

        binding.fragmentProductViewModel = viewModel
        binding.lifecycleOwner = this

        makeApiCallInCoroutines()

        return binding.root
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
        viewModel.fetchProductInCoroutine()

        return viewModel
    }
}