package juhyang.practice.mvvmexample

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.GlobalScope

/**

 * Created by juhyang on 5/1/21.

 */


class ContactRepository(application: Application) {

    private val contactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao: ContactDao = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return contacts
    }


    /*
    * Room DB 는 메인스레드에서 접근하려 하면 크래쉬가 발생한다.
    * */
    fun insert(contact: Contact) {
        try {
            GlobalScope.launch {
                contactDao.insert(contact)
            }
        } catch (e: Exception) {
        }
    }

    /*
    * Room DB 는 메인스레드에서 접근하려 하면 크래쉬가 발생한다.
    * */
    fun delete(contact: Contact) {
        try {
            GlobalScope.launch {
                contactDao.delete(contact)
            }
        } catch (e: Exception) {
        }
    }
}
