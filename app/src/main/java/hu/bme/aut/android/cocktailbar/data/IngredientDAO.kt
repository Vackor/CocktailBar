package hu.bme.aut.android.cocktailbar.data

import androidx.room.*

@Dao
interface IngredientDAO {
    @Query("SELECT * FROM ingredient")
    fun getAll(): List<Ingredient>

    @Insert
    fun insert(shoppingItems: Ingredient): Long

    @Update
    fun update(shoppingItem: Ingredient)

    @Delete
    fun deleteItem(shoppingItem: Ingredient)
}