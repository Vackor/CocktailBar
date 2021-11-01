package hu.bme.aut.android.cocktailbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.cocktailbar.adapter.StockAdapter
import hu.bme.aut.android.cocktailbar.data.Ingredient
import hu.bme.aut.android.cocktailbar.data.IngredientDatabase
import hu.bme.aut.android.cocktailbar.data.Stock
import hu.bme.aut.android.cocktailbar.database_fragment.newIngredientDialogFragment
import hu.bme.aut.android.cocktailbar.databinding.ActivityDatabaseBinding
import kotlin.concurrent.thread

class DatabaseActivity : AppCompatActivity(), StockAdapter.IngredientClickListener,
        newIngredientDialogFragment.NewIngredientDialogListener
{

    private lateinit var binding: ActivityDatabaseBinding

    private lateinit var database: IngredientDatabase
    private lateinit var adapter: StockAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = IngredientDatabase.getDatabase(applicationContext)

        binding.fab.setOnClickListener {
            newIngredientDialogFragment()
                .show(supportFragmentManager, newIngredientDialogFragment.TAG)
        }

        setTitle("Database")

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = StockAdapter(this)
        binding.rvDatabase.layoutManager = LinearLayoutManager(this)
        binding.rvDatabase.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.ingredientDao().getAll()
            Stock.setItems(items)
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: Ingredient) {
        thread {
            database.ingredientDao().update(item)
            Log.d("MainActivity", "Ingredient update was successful")
        }
    }

    override fun onIngredientCreated(newItem: Ingredient) {
        thread {
            val insertId = database.ingredientDao().insert(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }

    override fun onItemDeleted(item: Ingredient, index: Int) {
        thread {
            database.ingredientDao().deleteItem(item)

            runOnUiThread {
                adapter.deleteItem(item, index)
            }

            Log.d("MainActivity", "Ingredient delete was successful")
        }
    }
}

