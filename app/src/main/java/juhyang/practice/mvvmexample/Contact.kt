package juhyang.practice.mvvmexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**

 * Created by juhyang on 5/1/21.

 */

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "number")
    var number: String,

    @ColumnInfo(name = "initial")
    var initial: Char
) {
    constructor() : this(null, "", "", '\u0000')
    constructor(name: String, number: String, initial: Char) : this(null, name, number, initial)
}
