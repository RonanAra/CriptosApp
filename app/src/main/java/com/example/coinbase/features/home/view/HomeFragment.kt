package com.example.coinbase.features.home.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinbase.databinding.FragmentHomeBinding
import com.example.coinbase.features.home.viewmodel.HomeViewModel
import com.example.coinbase.model.Data
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModel()

    private val coinAdapter: CoinAdapter by lazy {
        CoinAdapter { assetClicked ->
            onClicked(assetClicked)
        }
    }

    private fun onClicked(assetClicked: Data?) {
        assetClicked?.website?.let {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            )
        } ?: run {
            binding?.rvCoinBase?.let {
                Snackbar.make(
                    it,
                    "This asset haven't a website",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
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

        viewModel.getCoinBase()
        setupObservables()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding?.rvCoinBase?.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = coinAdapter
        }
    }


    private fun setupObservables() {
        viewModel.onSuccessCoin.observe(viewLifecycleOwner) {
            it?.let {
                coinAdapter.submitList(it)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}