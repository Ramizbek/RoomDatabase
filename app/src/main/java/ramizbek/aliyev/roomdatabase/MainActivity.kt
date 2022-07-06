package ramizbek.aliyev.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.channels.ticker
import ramizbek.aliyev.roomdatabase.adapters.RvAdapter
import ramizbek.aliyev.roomdatabase.database.AppDatabase
import ramizbek.aliyev.roomdatabase.database.entity.Ticket
import ramizbek.aliyev.roomdatabase.databinding.ActivityMainBinding
import ramizbek.aliyev.roomdatabase.databinding.ItemDialogBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), RvAdapter.RvClick {
    private lateinit var binding:ActivityMainBinding
    private lateinit var list: ArrayList<Ticket>
    private lateinit var appDatabase: AppDatabase
    private lateinit var rvAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)
        list = ArrayList()

        list.addAll(appDatabase.ticketDao().getAllTicket())
        rvAdapter = RvAdapter(list, this)

        binding.apply {
            rv.adapter= rvAdapter
            btnSave.setOnClickListener {
                val ticket = Ticket(
                    edtName.text.toString(),
                    Random.nextInt(100),
                    edtLocation.text.toString()
                )
                appDatabase.ticketDao().addTicket(ticket)
                list.add(ticket)
                rvAdapter.notifyItemInserted(list.size -1)
            }
        }
    }

    override fun longClick(ticket: Ticket, position: Int) {
        appDatabase.ticketDao().deleteTicket(ticket)
        list.remove(ticket)
        rvAdapter.notifyItemRemoved(position)
    }

    override fun onClick(ticket: Ticket, position: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val itemDialog = ItemDialogBinding.inflate(layoutInflater)
        itemDialog.edtName.setText(ticket.name)
        itemDialog.edtLocation.setText(ticket.location)
        itemDialog.btnSave.setOnClickListener {
            ticket.name = itemDialog.edtName.text.toString()
            ticket.location = itemDialog.edtLocation.text.toString()


            appDatabase.ticketDao().editTicket(ticket)
            list[position] = ticket
            rvAdapter.notifyItemChanged(position)
            dialog.cancel()
        }
        dialog.setView(itemDialog.root)
        dialog.show()
    }
}