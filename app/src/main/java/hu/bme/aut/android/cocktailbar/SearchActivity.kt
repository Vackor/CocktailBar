package hu.bme.aut.android.cocktailbar

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.cocktailbar.adapter.ResultAdapter
import hu.bme.aut.android.cocktailbar.data.ResultDatabase
import hu.bme.aut.android.cocktailbar.data.ResultItem
import hu.bme.aut.android.cocktailbar.databinding.ActivitySearchBinding
import hu.bme.aut.android.cocktailbar.details.DetailsFragment
import hu.bme.aut.android.cocktailbar.model.CocktailData
import hu.bme.aut.android.cocktailbar.network.NetworkManager
import hu.bme.aut.android.cocktailbar.ui.home.HomeDataHolder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class SearchActivity : AppCompatActivity(), ResultAdapter.ResultItemClickListener,
    HomeDataHolder {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var database: ResultDatabase
    private lateinit var adapter: ResultAdapter
    private var cocktailData: CocktailData? = null
    private var homeDataHolder: HomeDataHolder? = null
    private var keyWord: String = ""
    private var results: MutableList<ResultItem?>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = ResultDatabase.getDatabase(applicationContext)

        setTitle("Search")
        initRecyclerView()

        binding.searchButton.setOnClickListener {
            keyWord = binding.searchText.text.toString()
            adapter.nukeTable()
            if (keyWord != null) {
                loadCocktailData()
            }
        }

        homeDataHolder = if (this is HomeDataHolder) {
            this as HomeDataHolder?
        } else {
            throw RuntimeException(
                "Activity must implement CocktailDataHolder interface!"
            )
        }

        adapter.nukeTable()
        var no = homeDataHolder?.getCocktailData()?.drinks?.size

        if (no != null && no != 0) {
            for (i in 0..no) {
                if (results?.get(i) != null) {
                    val tmp = results?.get(i)!!
                    database.resultItemDao().deleteItem(tmp)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        adapter.nukeTable()
        var no = homeDataHolder?.getCocktailData()?.drinks?.size

        if (no != null && no != 0) {
            for (i in 0..no) {
                if (results?.get(i) != null) {
                    val tmp = results?.get(i)!!
                    database.resultItemDao().deleteItem(tmp)
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = ResultAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
    }

    override fun onItemClicked(item: ResultItem) {
        if (NetworkManager.cocktail)
        DetailsFragment().show(supportFragmentManager, DetailsFragment.TAG)
        // Az API hozzávaló alapján történő kereséskor csak a nevét adja vissza a koktélnak és nem
        // akartam még egy API hivást végezni és egy újabb tömbben tárolni annak az eredményét,
        // ezért megnyitni a részleteket csak név szerinti keresésnél lehet
    }

    override fun getCocktailData(): CocktailData? {
        return cocktailData
    }

    private fun loadCocktailData() {
        if(NetworkManager.cocktail){
            NetworkManager.getCocktailResults(keyWord)?.enqueue(object : Callback<CocktailData?> {
                override fun onResponse(
                    call: Call<CocktailData?>,
                    response: Response<CocktailData?>
                ) {
                    Log.d(ContentValues.TAG, "onResponse: " + response.code())
                    if (response.isSuccessful) {
                        displayCocktailData(response.body())
                    } else {
                        Toast.makeText(this@SearchActivity, "Error: " + response.message(),
                            Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(
                    call: Call<CocktailData?>,
                    throwable: Throwable
                ) {
                    throwable.printStackTrace()
                    Toast.makeText(this@SearchActivity,
                        "Connection failed", Toast.LENGTH_LONG).show()
                }
            })
        }
        else {
        NetworkManager.getIngredientResults(keyWord)?.enqueue(object : Callback<CocktailData?> {
                    override fun onResponse(
                        call: Call<CocktailData?>,
                        response: Response<CocktailData?>
                    ) {
                        Log.d(ContentValues.TAG, "onResponse: " + response.code())
                        if (response.isSuccessful) {
                            displayCocktailData(response.body())
                        } else {
                            Toast.makeText(this@SearchActivity, "Error: " + response.message(),
                                Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<CocktailData?>,
                        throwable: Throwable
                    ) {
                        throwable.printStackTrace()
                        Toast.makeText(this@SearchActivity,
                            "Connection failed", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

    private fun displayCocktailData(receivedCocktailData: CocktailData?) {
        cocktailData = receivedCocktailData
        displayCocktail()
    }

    private fun displayCocktail() {
        NetworkManager.setCurrentCocktails(homeDataHolder?.getCocktailData())

        var no = homeDataHolder?.getCocktailData()?.drinks?.size

        if (no != null && no > 0){
            NetworkManager.size = no
            for (i in 0..no - 1){
                if (results?.get(i) != null) {
                    val tmp = results?.get(i)!!
                    database.resultItemDao().deleteItem(tmp)
                }
            }
            for (i in 0..no - 1){
                val newItemName = homeDataHolder?.getCocktailData()?.drinks?.get(i)?.strDrink
                if (newItemName != null) {
                    val resultItem = ResultItem(name = newItemName)
                    results?.add(resultItem)
                    thread {
                        val insertId = database.resultItemDao().insert(resultItem)
                        resultItem.id = insertId
                        runOnUiThread {
                            adapter.addItem(resultItem)
                        }
                    }
                }
            }
        }
    }
}
