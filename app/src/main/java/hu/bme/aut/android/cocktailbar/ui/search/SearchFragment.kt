package hu.bme.aut.android.cocktailbar.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.cocktailbar.DatabaseActivity
import hu.bme.aut.android.cocktailbar.SearchActivity
import hu.bme.aut.android.cocktailbar.databinding.FragmentSearchBinding
import hu.bme.aut.android.cocktailbar.network.NetworkManager

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val isCocktail: Boolean = NetworkManager.cocktail

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchByCocktail.setOnClickListener {
            val intent = Intent(this.context, SearchActivity::class.java)
            NetworkManager.cocktail = true
            startActivity(intent)
        }
        binding.searchByIngredient.setOnClickListener {
            val intent = Intent(this.context, SearchActivity::class.java)
            NetworkManager.cocktail = false
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}