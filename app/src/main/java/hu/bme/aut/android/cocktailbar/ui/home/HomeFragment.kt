package hu.bme.aut.android.cocktailbar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import hu.bme.aut.android.cocktailbar.databinding.FragmentHomeBinding
import hu.bme.aut.android.cocktailbar.details.DetailsFragment
import hu.bme.aut.android.cocktailbar.network.NetworkManager

class HomeFragment : Fragment(){

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private var homeDataHolder: HomeDataHolder? = null

    private val binding get() = _binding!!

    private var first = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeDataHolder = if (activity is HomeDataHolder) {
            activity as HomeDataHolder?
        } else {
            throw RuntimeException(
                "Activity must implement CocktailDataHolder interface!"
            )
        }

        val textView:TextView = binding.randomName
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        binding.randomButton.setOnClickListener{
            displayCocktail()
        }

        binding.randomFrame.setOnClickListener {
            if (binding.randomName.text != "Try something new today!") {
                NetworkManager.clickedIndex = 0
                var dialog = DetailsFragment()
                fragmentManager?.let {it1 -> DetailsFragment().show(it1, DetailsFragment.TAG)}
            }
        }
        first = false

        return root
    }

    private fun displayCocktail() {
        NetworkManager.getCocktail()
        NetworkManager.setCurrentCocktails(homeDataHolder?.getCocktailData())

        var name = homeDataHolder?.getCocktailData()?.drinks?.get(0)?.strDrink
        val textView: TextView = binding.randomName
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = name
        })

        var pic = homeDataHolder?.getCocktailData()?.drinks?.get(0)?.strDrinkThumb
        Glide.with(this)
            .load(pic)
            .transition(DrawableTransitionOptions().crossFade())
            .into(binding.randomImage)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (homeDataHolder?.getCocktailData() != null) {
            displayCocktail()
        }
        else{
            val name = "Try something new today!"
            val textView:TextView = binding.randomName
            homeViewModel.text.observe(viewLifecycleOwner, Observer {
                textView.text = name
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}