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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater)
        var rootView: View = binding.root

        cocktail = NetworkManager.getCurrentCocktails()
        if (cocktail?.drinks?.get(0)?.strDrink.toString() != "null"){
            binding.name.text = cocktail?.drinks?.get(0)?.strDrink.toString()
            setIngredients()
            binding.ingredient.text = ingredients
            binding.measure.text = measure
        }
        if (cocktail?.drinks?.get(0)?.strInstructions.toString() != "null")
            binding.instructionsEN.text = cocktail?.drinks?.get(0)?.strInstructions.toString()
        else if (cocktail?.drinks?.get(0)?.strDrink.toString() != "null")
            binding.instructionsEN.text = ""
        return rootView
    }

    companion object {
        const val TAG = "DetailsFragment"
    }

    private fun setIngredients(){
        if (cocktail?.drinks?.get(0)?.strIngredient1.toString() != "null"){
            ingredients += cocktail?.drinks?.get(0)?.strIngredient1.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure1.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient2.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient2.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure2.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient3.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient3.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure3.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient4.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient4.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure4.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient5.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient5.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure5.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient6.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient6.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure6.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient7.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient7.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure7.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient8.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient8.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure8.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient9.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient9.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure9.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient10.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient10.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure10.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient11.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient11.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure11.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient12.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient12.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure12.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient13.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient13.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure13.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient14.toString() != "null") {
            ingredients += cocktail?.drinks?.get(0)?.strIngredient14.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure14.toString() + "\n"
        }
        if (cocktail?.drinks?.get(0)?.strIngredient15.toString() != "null"){
            ingredients += cocktail?.drinks?.get(0)?.strIngredient15.toString() + "\n"
            measure += cocktail?.drinks?.get(0)?.strMeasure15.toString() + "\n"
        }

    }
}