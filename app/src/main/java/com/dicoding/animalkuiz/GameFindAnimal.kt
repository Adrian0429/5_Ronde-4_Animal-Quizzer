
package com.dicoding.animalkuiz
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.dicoding.animalkuiz.data.viewmodels.QuestViewModel
import com.google.android.material.button.MaterialButton

class GameFindAnimal : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel: QuestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_find_animal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuestViewModel::class.java)
        val clueTextView = view.findViewById<TextView>(R.id.tv_clue)
        val silhouetteImageView = view.findViewById<ImageView>(R.id.imageView2)
        navController = Navigation.findNavController(view)
        val buttonGame = view.findViewById<MaterialButton>(R.id.buttonGame)

        // Set OnClickListener for the button
        buttonGame.setOnClickListener {
            navController.navigate(R.id.ScanFragment)
        }

        viewModel.questData.observe(viewLifecycleOwner, Observer { questResponse ->
            questResponse?.let { response ->
                if (response.success == true) {
                    val questData = response.data
                    clueTextView.text = "Petunjuk: ${questData?.hint}"

                    // Load the silhouette image using Glide
                    val imageUrl = questData?.silhouetteImage
                    if (!imageUrl.isNullOrEmpty()) {
                        Glide.with(this)
                            .load("https://tf4zs3dh-8080.asse.devtunnels.ms/api/v1/files/$imageUrl")
                            .into(silhouetteImageView)
                    }
                } else {
                    Toast.makeText(requireContext(), "Error: ${response.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.fetchQuestForUserByAnimalId("e9228b40-044f-4cc2-b3f8-ec41ab838dbf")

    }

}
