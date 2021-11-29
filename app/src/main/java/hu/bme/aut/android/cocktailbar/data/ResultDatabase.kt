package hu.bme.aut.android.cocktailbar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ResultItem::class], version = 1)
abstract class ResultDatabase : RoomDatabase() {
    abstract fun resultItemDao(): ResultItemDAO
    companion object {
        fun getDatabase(applicationContext: Context): ResultDatabase {
            return Room.databaseBuilder(
                applicationContext,
                ResultDatabase::class.java,
                "resultitem"
            ).build();
        }
    }
}
