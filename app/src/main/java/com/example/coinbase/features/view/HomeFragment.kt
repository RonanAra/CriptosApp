package com.example.coinbase.features.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coinbase.databinding.FragmentHomeBinding
import com.example.coinbase.features.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModel()


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

    }


    private fun setupObservables() {
        activity?.let {
            viewModel.onSuccessCoin.observe(viewLifecycleOwner) {
                if (it != null && it.isNotEmpty()) {
                    it.let {
                        val coinAdapter = CoinAdapter(
                            listaCriptos = it

                        ) { clicked ->
                            openLink(clicked.website)
                        }

                        binding?.let {
                            with(it) {
                                rvCoinBase.apply {
                                    layoutManager = GridLayoutManager(context, 2)
                                    adapter = coinAdapter

                                }
                            }
                        }

                    }
                } else {
                        binding?.progressBar?.visibility = View.VISIBLE
                        Toast.makeText(context, "Error in call Api", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}