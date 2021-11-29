package hu.bme.aut.android.cocktailbar.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.cocktailbar.SearchActivity
import hu.bme.aut.android.cocktailbar.data.ResultDatabase
import hu.bme.aut.android.cocktailbar.data.ResultItem
import hu.bme.aut.android.cocktailbar.data.Stock
import hu.bme.aut.android.cocktailbar.databinding.ItemResultListBinding
import hu.bme.aut.android.cocktailbar.details.DetailsFragment
import hu.bme.aut.android.cocktailbar.network.NetworkManager
import kotlin.concurrent.thread

class ResultAdapter (private val listener: ResultItemClickListener) :
    RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {

    private val items = mutableListOf<ResultItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ResultViewHolder(
        ItemResultListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val resultItem = items[position]
        holder.binding.tvName.text = resultItem.name

        holder.binding.tvName.setOnClickListener {
            NetworkManager.clickedIndex = position
            val resultItem = items[position]
            listener.onItemClicked(resultItem)
        }
    }

    override fun getItemCount(): Int = items.size

    interface ResultItemClickListener {
        fun onItemClicked(item: ResultItem)
    }

    inner class ResultViewHolder(val binding: ItemResultListBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun addItem(item: ResultItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun nukeTable(){
        items.clear()
        notifyDataSetChanged()
    }
}
