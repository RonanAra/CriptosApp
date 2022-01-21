package com.example.coinbase.features.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coinbase.base.BaseFragment
import com.example.coinbase.databinding.FragmentHomeBinding
import com.example.coinbase.features.viewmodel.HomeViewModel
import com.example.coinbase.utils.Command
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment() {

    private var binding: FragmentHomeBinding? = null
    override var command: MutableLiveData<Command> = MutableLiveData()

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

        viewModel.command = command
        viewModel.getCoinBase()

        setupObservables()

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