package com.example.revision

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.revision.databinding.DiceRollFragmentBinding

class DiceRollFragment : Fragment(R.layout.dice_roll_fragment) {

    private lateinit var binding: DiceRollFragmentBinding
    //    lateinit var dice_image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dice_roll_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val btn_roll = findViewById<Button>(R.id.btn_roll)
//        dice_image = findViewById(R.id.dice_image)

        binding.btnRoll.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val random_num = (1..6).random()
        val dice_res: Int = when(random_num){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        binding.diceImage.setImageResource(dice_res)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(activity, "Exiting Dice Roller...", Toast.LENGTH_SHORT).show()
    }

}