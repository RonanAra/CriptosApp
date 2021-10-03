package com.example.coinbase.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coinbase.R
import com.example.coinbase.features.view.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, HomeFragment())
            .commit()
    }
}