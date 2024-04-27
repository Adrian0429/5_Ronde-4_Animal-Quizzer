package com.dicoding.animalkuiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class QuestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)
        val buttonReptil: Button = findViewById(R.id.buttonreptil)
        val buttonAves: Button = findViewById(R.id.buttonaves)
        val buttonPisces: Button = findViewById(R.id.buttonPisces)
        val buttonMamalia: Button = findViewById(R.id.buttonmamalia)

        buttonReptil.setOnClickListener {
            navigateToGameFragment("e9228b40-044f-4cc2-b3f8-ec41ab838dbf")
        }

        buttonAves.setOnClickListener {
            navigateToGameFragment("9829499b-0b6b-4f2a-b3a8-bb15b0315a95")
        }

        buttonPisces.setOnClickListener {
            navigateToGameFragment("d552c432-62d2-4f93-a2df-9400f5eb5774")
        }

        buttonMamalia.setOnClickListener {
            navigateToGameFragment("db9ebfa1-1746-43be-a434-17364c25cc04")
        }
    }

    private fun navigateToGameFragment(animalType: String) {
        // Create an intent to navigate to GameFragment
        val intent = Intent(this, MainActivity::class.java)

        // Add the animal type as an intent extra
        intent.putExtra("animal_type", animalType)

        startActivity(intent)
    }
}
