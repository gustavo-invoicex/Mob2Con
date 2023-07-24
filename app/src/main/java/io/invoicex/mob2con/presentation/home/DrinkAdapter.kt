package io.invoicex.mob2con.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.invoicex.mob2con.databinding.DrinkItemBinding
import io.invoicex.mob2con.domain.model.Drink
import io.invoicex.mob2con.domain.model.toImage

class DrinkAdapter(
    private val drinks: List<Drink>
) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {

    private lateinit var binding: DrinkItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        binding = DrinkItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return DrinkViewHolder(binding.root)
    }


    override fun getItemCount(): Int = drinks.size

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) =
        holder.bind(drink = drinks[position])

    inner class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(drink: Drink) {
            with(binding) {
                drinkName.text = drink.name
                drinkPrice.text = "R$ ${drink.value}"
                drinkImage.setImageResource(drink.toImage())
            }
        }
    }
}