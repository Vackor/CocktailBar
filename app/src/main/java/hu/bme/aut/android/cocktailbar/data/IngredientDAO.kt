package hu.bme.aut.android.cocktailbar.data

import androidx.room.*

@Dao
interface IngredientDAO {
    @Query("SELECT * FROM ingredient")
    fun getAll(): List<Ingredient>

    @Insert
    fun insert(ingredient: Ingredient): Long

    @Update
    fun update(ingredient: Ingredient)

    @Delete
    fun deleteItem(ingredient: Ingredient)
}