package hu.bme.aut.android.cocktailbar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Ingredient::class], version = 1)
abstract class IngredientDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientDAO

    companion object {
        fun getDatabase(applicationContext: Context): IngredientDatabase {
            return Room.databaseBuilder(
                applicationContext,
                IngredientDatabase::class.java,
                "ingredients"
            ).build();
        }
    }
}