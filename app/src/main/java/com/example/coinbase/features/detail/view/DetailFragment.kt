package com.example.coinbase.features.detail.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.coinbase.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private var webView: WebView? = null
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = binding?.webView

        webView?.settings?.javaScriptEnabled = true
        webView?.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding?.pdDetailLoading?.isVisible = false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding?.pdDetailLoading?.isVisible = true
            }
        }
        webView?.loadUrl(args.url.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.root
    }

}