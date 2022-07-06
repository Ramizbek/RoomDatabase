package ramizbek.aliyev.roomdatabase.database.dao

import androidx.room.*
import ramizbek.aliyev.roomdatabase.database.entity.Ticket

@Dao
interface TicketDao {

    @Insert
    fun addTicket(ticket: Ticket)

    @Query("select * from ticket")
    fun getAllTicket():List<Ticket>

    @Delete
    fun deleteTicket(ticket: Ticket)

    @Update
    fun editTicket(ticket: Ticket)
}