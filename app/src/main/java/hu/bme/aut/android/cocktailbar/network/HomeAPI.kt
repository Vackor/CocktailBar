package hu.bme.aut.android.cocktailbar.network

import hu.bme.aut.android.cocktailbar.model.CocktailData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface HomeAPI {
    @GET("random.php?")
    fun getRandom(
    ): Call<CocktailData?>?

    @GET("search.php?")
    fun getCocktailResults(
        @Query("s") cocktailName: String
    ): Call<CocktailData?>?

    @GET("filter.php?")
    fun getIngredientResults(
        @Query("i") ingredientName: String
    ): Call<CocktailData?>?
}