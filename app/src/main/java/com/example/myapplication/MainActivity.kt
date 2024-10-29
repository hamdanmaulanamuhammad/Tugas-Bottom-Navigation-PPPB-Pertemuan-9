package com.example.myapplication

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        with(binding) {
            val navController = findNavController(R.id.nav_host_fragment)
            bottomNavigationMenu.setupWithNavController(navController)

            // Set ActionBar background color
            supportActionBar?.setBackgroundDrawable(
                ColorDrawable(ContextCompat.getColor(root.context, R.color.primary))
            )

            // Custom ActionBar title with Poppins font and white color
            val titleTextView = TextView(this@MainActivity).apply {
                text = Html.fromHtml("<font color='#FFFFFF'><b>TravelApp</b></font>", Html.FROM_HTML_MODE_LEGACY)
                typeface = ResourcesCompat.getFont(this@MainActivity, R.font.poppins) // Pastikan Anda menambahkan font Poppins di folder res/font
                textSize = 20f // Set ukuran font sesuai keinginan
                setTextColor(ContextCompat.getColor(this@MainActivity, android.R.color.white)) // Warna putih
            }

            supportActionBar?.customView = titleTextView
            supportActionBar?.displayOptions = androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
