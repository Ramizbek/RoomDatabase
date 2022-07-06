package ramizbek.aliyev.roomdatabase.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ramizbek.aliyev.roomdatabase.database.entity.Ticket
import ramizbek.aliyev.roomdatabase.databinding.ItemRvBinding

class RvAdapter(var list: ArrayList<Ticket>, val rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.VH>() {

    inner class VH(var itemRV: ItemRvBinding) : RecyclerView.ViewHolder(itemRV.root) {
        fun onBind(ticket: Ticket, position: Int) {
            itemRV.tvName.text = ticket.name
            itemRV.tvLocation.text = ticket.location
            itemRV.tvCode.text = ticket.ticket_code.toString()

            itemRV.root.setOnLongClickListener {
                rvClick.longClick(ticket, position); true
            }
            itemRV.root.setOnClickListener {
                rvClick.onClick(ticket,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
    interface RVClickCourses {
        fun onClick(ticket: Ticket)

    }

    interface RvClick {
        fun longClick(ticket: Ticket, position: Int)
        fun onClick(ticket: Ticket, position: Int)
    }
}