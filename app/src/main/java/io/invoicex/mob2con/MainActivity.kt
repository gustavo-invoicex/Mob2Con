package io.invoicex.mob2con

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Mob2Con)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}