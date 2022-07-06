package ramizbek.aliyev.roomdatabase.database.entity

import androidx.room.*

@Entity
class Ticket {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var name:String? = null
    var ticket_code:Int? = null
    var location:String? = null

    constructor(id: Int?, name: String?, ticket_code: Int?, location: String?) {
        this.id = id
        this.name = name
        this.ticket_code = ticket_code
        this.location = location
    }

    constructor()


    constructor(name: String?, ticket_code: Int?, location: String?) {
        this.name = name
        this.ticket_code = ticket_code
        this.location = location
    }
}


