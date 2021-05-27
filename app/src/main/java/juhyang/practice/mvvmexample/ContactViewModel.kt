package juhyang.practice.mvvmexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**

 * Created by juhyang on 5/1/21.

 */
class ContactViewModel(application : Application) : AndroidViewModel(application) {

    private val repository = ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll() : LiveData<List<Contact>> {
        return this.contacts
    }

    fun insert(contact : Contact) {
        repository.insert(contact)
    }

    fun delete(contact : Contact) {
        repository.delete(contact)
    }
}
