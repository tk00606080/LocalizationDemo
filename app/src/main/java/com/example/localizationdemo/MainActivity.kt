package com.example.localizationdemo

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.localizationdemo.databinding.ActivityMainBinding
import com.example.localizationdemo.utils.ContextUtils
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var localeUpdatedContext: ContextWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        binding.btnEnglish.setOnClickListener(listener)
        binding.btnChinese.setOnClickListener(listener)
        binding.btnHindi.setOnClickListener(listener)
    }

    override fun attachBaseContext(newBase: Context?) {
        var locale = Locale(ContextUtils.loadLocale(newBase!!))
        localeUpdatedContext = newBase?.let { ContextUtils.updateLocale(it, locale) }!!
        super.attachBaseContext(localeUpdatedContext)
    }

    private val listener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.btn_english -> ContextUtils.saveLocale(applicationContext, "en")
            R.id.btn_chinese -> ContextUtils.saveLocale(applicationContext, "zh")
            R.id.btn_hindi -> ContextUtils.saveLocale(applicationContext, "hi")
        }
        recreate()
    }
}
