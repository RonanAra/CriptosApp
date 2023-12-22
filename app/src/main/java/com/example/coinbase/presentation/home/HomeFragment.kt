package com.example.coinbase.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coinbase.databinding.FragmentHomeBinding
import com.example.coinbase.data.models.response.CoinResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private val coinAdapter: CoinAdapter by lazy {
        CoinAdapter { assetClicked ->
            onClicked(assetClicked)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(
            view,
            savedInstanceState
        )
        setObservers()
        setupRecyclerView()
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCoins()
    }

    private fun setListeners() = binding.run {
        search.editText?.doAfterTextChanged {
            viewModel.filterList(it.toString())
        }
    }

    private fun onClicked(assetClicked: CoinResponse?) {
        assetClicked?.website?.let { url ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(url)
            )
        }
    }

    private fun setupRecyclerView() {
        binding.rvCoinBase.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = coinAdapter
        }
    }

    private fun setObservers() {
        viewModel.listCoins.observe(viewLifecycleOwner) { listCoins ->
            coinAdapter.submitList(listCoins)
        }
    }
}