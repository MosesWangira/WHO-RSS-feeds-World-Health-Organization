package com.example.diseaseoutbreaks.ui.maternal

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
import com.example.diseaseoutbreaks.data.Model.maternal.MaternalDataClass
import com.example.diseaseoutbreaks.databinding.FragmentMaternalBinding
import com.example.diseaseoutbreaks.util.animate
import com.example.diseaseoutbreaks.util.toast
import com.example.diseaseoutbreaks.viewmodels.MaternalViewModel

class Maternal : Fragment() {

    lateinit var binding: FragmentMaternalBinding

    lateinit var viewModel: MaternalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_maternal, container, false)

        Log.d("Disease: ", "Disease ViewModel Called")
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
                binding.maternalRecyclerView.apply {
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