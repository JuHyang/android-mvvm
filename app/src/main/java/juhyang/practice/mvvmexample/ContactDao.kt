package juhyang.practice.mvvmexample

import androidx.lifecycle.LiveData
import androidx.room.*

/**

 * Created by juhyang on 5/1/21.

 */

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll() : LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact : Contact)

    @Delete
    fun delete(contact : Contact)
}
