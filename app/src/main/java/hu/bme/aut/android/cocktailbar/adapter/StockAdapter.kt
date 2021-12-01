package hu.bme.aut.android.cocktailbar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.cocktailbar.data.Ingredient
import hu.bme.aut.android.cocktailbar.data.Stock
import hu.bme.aut.android.cocktailbar.databinding.ItemListBinding

class StockAdapter (private val listener: IngredientClickListener):
    RecyclerView.Adapter<StockAdapter.IngredientViewHolder>() {

    private var items = mutableListOf<Ingredient>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IngredientViewHolder(
        ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = items[position]

        holder.binding.ingredientName.text = ingredient.name
        holder.binding.inStock.isChecked = ingredient.inStock

        holder.binding.inStock.setOnCheckedChangeListener { buttonView, isChecked ->
            ingredient.inStock = isChecked
            listener.onItemChanged(ingredient)
        }

        holder.binding.removeIngredient.setOnClickListener {
            listener.onItemDeleted(ingredient, position)
        }
    }

    fun addItem(item: Ingredient) {
        items.add(item)
        Stock.setItems(items)
        notifyItemInserted(items.size - 1)
    }

    fun update(ingredients: List<Ingredient>) {
        items.clear()
        items.addAll(ingredients)
        Stock.setItems(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    fun deleteItem(item: Ingredient, index: Int){
        items.remove(item)
        Stock.setItems(items)
        notifyDataSetChanged()
    }

    interface IngredientClickListener {
        fun onItemChanged(item: Ingredient)
        fun onItemDeleted(item: Ingredient, index: Int)
    }

    inner class IngredientViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root)
}