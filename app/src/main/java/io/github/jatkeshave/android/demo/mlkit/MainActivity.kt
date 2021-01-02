package io.github.jatkeshave.android.demo.mlkit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.jatkeshave.android.demo.mlkit.databinding.ActivityMainBinding
import io.github.jatkeshave.android.demo.mlkit.ui.BarcodeActivity

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hookViews()
    }

    private fun hookViews() {
        binding.mbtnBarcode.setOnClickListener {
            startActivity(Intent(this@MainActivity, BarcodeActivity::class.java))
        }
    }
}