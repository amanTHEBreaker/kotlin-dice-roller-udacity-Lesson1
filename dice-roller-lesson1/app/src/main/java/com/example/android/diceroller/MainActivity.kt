package com.example.android.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    /*
    Normally, properties declared as having a non-null type must be initialized in the constructor.
    however, it is often the case that doing so is not convenient, as we do not define constructor
    in activity but you still want to avoid null checks when referencing the property
    inside the body of a class, thus we use lateinit
     */

    private lateinit var diceImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // getting reference with immutable variable
        val rollButton: Button = findViewById(R.id.roll_button)

        rollButton.text = "Let's Roll"
        // "Let's Roll".also { rollButton.text = it }

        rollButton.setOnClickListener {
            // Toast.makeText(this, "Button clicked",Toast.LENGTH_SHORT).show();
            rollDice()
        }
        diceImage = findViewById(R.id.dice_image)
    }

    private fun rollDice() {

        val randomInt = Random().nextInt(6) + 1

        val drawableResource = when(randomInt) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)

        /*
        val resultText: TextView = findViewById(R.id.result_text)
        // under java.util.*
        // generates 1 to 6 random values
        val randomInt = Random().nextInt(6) + 1
        resultText.text = randomInt.toString()
         */
    }
}
