package com.example.proguardtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.igalata.bubblepicker.BubblePickerListener
import com.igalata.bubblepicker.adapter.BubblePickerAdapter
import com.igalata.bubblepicker.model.BubbleGradient
import com.igalata.bubblepicker.model.PickerItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBubblePicker()
    }

    override fun onResume() {
        super.onResume()
        picker.onResume()
    }

    override fun onPause() {
        super.onPause()
        picker.onPause()
    }

    private fun setupBubblePicker() {
        picker.bubbleSize = 50
        picker.centerImmediately = true
        picker.adapter = object : BubblePickerAdapter {

            val colors = resources.obtainTypedArray(R.array.colors)
            val titles = listOf("1", "2", "3", "4", "5", "6")

            val multiplier = 2
            val modulus = 8
            val addition = 1

            override val totalCount = titles.size // 1

            override fun getItem(position: Int): PickerItem { // 2
                return PickerItem().apply {
                    title = titles[position]

                    val start = colors.getColor((position * multiplier) % modulus,0)
                    val end = colors.getColor((position * multiplier) % modulus + addition,0)
                    gradient = BubbleGradient(start, end, BubbleGradient.VERTICAL)

                    textColor = ContextCompat.getColor(this@MainActivity, android.R.color.white)
                }
            }

        }

        picker.listener = object : BubblePickerListener { // 3

            override fun onBubbleSelected(item: PickerItem) {

            }

            override fun onBubbleDeselected(item: PickerItem) {

            }

        }
    }
}
