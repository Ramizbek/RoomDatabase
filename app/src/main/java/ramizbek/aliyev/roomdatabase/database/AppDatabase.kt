package ramizbek.aliyev.roomdatabase.database

import android.content.Context
import androidx.room.*
import ramizbek.aliyev.roomdatabase.database.dao.TicketDao
import ramizbek.aliyev.roomdatabase.database.entity.Ticket

@Database(entities = [Ticket::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun ticketDao(): TicketDao

    companion object {
        var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase{
            if (appDatabase == null){
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "ticket_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return appDatabase!!
        }
    }
}