package hu.bme.aut.android.cocktailbar.network

import hu.bme.aut.android.cocktailbar.model.Cocktail
import hu.bme.aut.android.cocktailbar.model.CocktailData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val retrofit: Retrofit
    private val homeApi: HomeAPI
    private var currentCocktails: CocktailData? = null

    private const val SERVICE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        homeApi = retrofit.create(HomeAPI::class.java)
    }

    fun getCocktail(): Call<CocktailData?>? {
        return homeApi.getRandom()
    }

    fun getCurrentCocktails(): CocktailData?{
        return currentCocktails
    }

    fun setCurrentCocktails(cd: CocktailData?){
        currentCocktails = cd
    }
}