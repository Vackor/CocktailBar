package hu.bme.aut.android.cocktailbar.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.cocktailbar.databinding.FragmentDetailsBinding
import hu.bme.aut.android.cocktailbar.model.CocktailData
import hu.bme.aut.android.cocktailbar.network.NetworkManager

class DetailsFragment: DialogFragment(){
    private var cocktail: CocktailData? = null
    private lateinit var binding: FragmentDetailsBinding
    private var ingredients = ""
    private var measure = ""
    private var index: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater)
        var rootView: View = binding.root

        index = NetworkManager.clickedIndex

        cocktail = NetworkManager.getCurrentCocktails()
        if (cocktail?.drinks?.get(index)?.strDrink.toString() != "null"){
            binding.name.text = cocktail?.drinks?.get(index)?.strDrink.toString()
            setIngredients()
            binding.ingredient.text = ingredients
            binding.measure.text = measure
        }
        if (cocktail?.drinks?.get(index)?.strInstructions.toString() != "null")
            binding.instructionsEN.text = cocktail?.drinks?.get(index)?.strInstructions.toString()
        else if (cocktail?.drinks?.get(index)?.strDrink.toString() != "null")
            binding.instructionsEN.text = ""
        return rootView
    }

    companion object {
        const val TAG = "DetailsFragment"
    }

    private fun setIngredients(){
        if (cocktail?.drinks?.get(index)?.strIngredient1.toString() != "null"){
            ingredients += cocktail?.drinks?.get(index)?.strIngredient1.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure1.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient2.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient2.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure2.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient3.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient3.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure3.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient4.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient4.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure4.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient5.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient5.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure5.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient6.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient6.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure6.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient7.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient7.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure7.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient8.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient8.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure8.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient9.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient9.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure9.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient10.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient10.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure10.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient11.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient11.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure11.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient12.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient12.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure12.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient13.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient13.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure13.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient14.toString() != "null") {
            ingredients += cocktail?.drinks?.get(index)?.strIngredient14.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure14.toString() + "\n"
        }
        if (cocktail?.drinks?.get(index)?.strIngredient15.toString() != "null"){
            ingredients += cocktail?.drinks?.get(index)?.strIngredient15.toString() + "\n"
            measure += cocktail?.drinks?.get(index)?.strMeasure15.toString() + "\n"
        }

    }
}