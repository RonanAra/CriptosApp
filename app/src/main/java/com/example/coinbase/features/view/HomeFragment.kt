package com.example.coinbase.features.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinbase.R
import com.example.coinbase.adapter.CoinAdapter
import com.example.coinbase.databinding.FragmentHomeBinding
import com.example.coinbase.features.viewmodel.HomeViewModel
import com.example.coinbase.model.Data


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(
            inflater, container, false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]

            viewModel.getCoinBase()

            setupObservables()


        }

    }

    private fun setupObservables() {
        activity?.let {
            viewModel.onSuccessCoin.observe(viewLifecycleOwner, {
                it?.let {
                    val coinAdapter = CoinAdapter(
                        listaCriptos = it

                    ){ clicked ->
                        openLink(clicked.website)
                    }

                    binding?.let {
                        with(it) {
                            rvCoinBase.apply {
                                layoutManager = GridLayoutManager(context,2)
                                adapter = coinAdapter

                            }
                        }
                    }

                }
            })
        }
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}