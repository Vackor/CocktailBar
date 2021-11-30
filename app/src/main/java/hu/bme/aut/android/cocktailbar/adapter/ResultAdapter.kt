package hu.bme.aut.android.cocktailbar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.cocktailbar.data.ResultItem
import hu.bme.aut.android.cocktailbar.databinding.ItemResultListBinding
import hu.bme.aut.android.cocktailbar.network.NetworkManager

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
            val resultItem = items[position]
            NetworkManager.clickedName = items[position].name
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
