package com.example.coinbase.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.databinding.FragmentHomeBinding
import com.example.coinbase.presentation.home.compose.components.ListCoins
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

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
        setListeners()
        setComposeView()
    }

    private fun setComposeView() {
        binding.listComposeView.setContent {
            ListCoins(
                homeViewModel = viewModel,
                onClickItem = { onClicked(it) }
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.handleIntent(HomeIntent.LoadCoins)
    }

    private fun setListeners() = binding.run {
        search.editText?.doAfterTextChanged {
            viewModel.handleIntent(HomeIntent.FilterList(it.toString()))
        }
        swipe.setOnRefreshListener {
            viewModel.handleIntent(HomeIntent.LoadCoins)
        }
    }

    private fun onClicked(assetClicked: CoinResponse?) {
        assetClicked?.website?.let { url ->
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(url)
            )
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state) {
                is HomeState.Error -> showError(state.message)
                is HomeState.Loading -> showLoading(state.loading)
            }
        }
    }

    private fun showLoading(loading: Boolean) {
        binding.swipe.isRefreshing = loading
    }

    private fun showError(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}