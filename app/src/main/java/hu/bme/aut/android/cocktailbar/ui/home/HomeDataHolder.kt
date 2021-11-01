package hu.bme.aut.android.cocktailbar.ui.home

import hu.bme.aut.android.cocktailbar.model.CocktailData

interface HomeDataHolder {
    fun getCocktailData(): CocktailData?
}