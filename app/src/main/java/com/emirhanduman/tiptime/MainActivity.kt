package com.emirhanduman.tiptime

import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.emirhanduman.tiptime.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculate() }


    }

    fun calculate() {
        val cost = binding.costOfService.text.toString().toDouble()
        val selectedId = binding.tipOptions.checkedRadioButtonId

        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = cost * tipPercentage

        val roundUp = binding.roundUpSwitch.isChecked

        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        NumberFormat.getCurrencyInstance()

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

        val toast = Toast.makeText(this, "Thanks for tip!", Toast.LENGTH_LONG)
        toast.show()

    }


}
