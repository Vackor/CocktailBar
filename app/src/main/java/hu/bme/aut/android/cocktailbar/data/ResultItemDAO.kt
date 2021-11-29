package hu.bme.aut.android.cocktailbar.data

import androidx.room.*

@Dao
interface ResultItemDAO {
    @Query("SELECT * FROM resultitem")
    fun getAll(): List<ResultItem>

    @Insert
    fun insert(resultItem: ResultItem): Long

    @Update
    fun update(resultItem: ResultItem)

    @Delete
    fun deleteItem(resultItem: ResultItem)

    @Query("DELETE FROM resultitem")
    fun nukeTable()
}
