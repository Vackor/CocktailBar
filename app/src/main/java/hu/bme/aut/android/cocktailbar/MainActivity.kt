package hu.bme.aut.android.cocktailbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.ContentValues.TAG
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import hu.bme.aut.android.cocktailbar.databinding.ActivityMainBinding
import hu.bme.aut.android.cocktailbar.model.CocktailData
import hu.bme.aut.android.cocktailbar.network.NetworkManager
import hu.bme.aut.android.cocktailbar.ui.home.HomeDataHolder
import hu.bme.aut.android.cocktailbar.details.DetailsFragment


class MainActivity : AppCompatActivity(), HomeDataHolder {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private var cocktailData: CocktailData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        var dialog = DetailsFragment()
        dialog.show(supportFragmentManager, "")

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_search, R.id.nav_database, R.id.nav_to_drink
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                true
            }
            R.id.action_exit -> {
                finishAffinity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun loadCocktailData() {
        NetworkManager.getCocktail()?.enqueue(object : Callback<CocktailData?> {
            override fun onResponse(
                call: Call<CocktailData?>,
                response: Response<CocktailData?>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.isSuccessful) {
                    displayCocktailData(response.body())
                } else {
                    Toast.makeText(this@MainActivity, "Error: " + response.message(),
                    Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(
                call: Call<CocktailData?>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()
                Toast.makeText(this@MainActivity,
                "Connection failed", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displayCocktailData(receivedCocktailData: CocktailData?) {
        cocktailData = receivedCocktailData
    }

    override fun getCocktailData(): CocktailData?{
        loadCocktailData()
        return cocktailData
    }
}