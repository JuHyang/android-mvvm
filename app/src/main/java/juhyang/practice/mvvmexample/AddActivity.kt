package juhyang.practice.mvvmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    private val contactViewModel : ContactViewModel by viewModels()

    private var id : Long? = null

    private lateinit var nameEditText : EditText
    private lateinit var numberEditText : EditText

    private lateinit var addButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        if (intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) && intent.hasExtra(EXTRA_CONTACT_NUMBER) && intent.hasExtra(EXTRA_CONTACT_ID)) {
            nameEditText.setText(intent.getStringExtra(EXTRA_CONTACT_NAME))
            numberEditText.setText(intent.getStringExtra(EXTRA_CONTACT_NUMBER))
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }

        initView()
        aboutView()
    }

    private fun initView() {
        nameEditText = findViewById(R.id.add_edittext_name)
        numberEditText = findViewById(R.id.add_edittext_number)
        addButton = findViewById(R.id.add_button)
    }

    private fun aboutView() {
        addButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val number = numberEditText.text.toString()

            if (name.isEmpty() || number.isEmpty()) {
                Toast.makeText(this, "Please enter name and number.", Toast.LENGTH_SHORT).show()
            } else {
                val initial = name[0].toUpperCase()
                val contact = Contact(name, number, initial)
                contactViewModel.insert(contact)
                finish()
            }
        }


    }

//    private fun initViewModel() {
//        /* ViewModel ??? ??????????????? ????????? ????????? ?????? ????????? ??????????????? ???????????? ?????? ???????????????.
//        * ?????????????????? ?????? ?????? ????????? ViewModel ??????????????? ????????? ?????? ????????? ???????????? ????????? ????????? ????????????.
//        * ????????? ViewModelProvider ??? ???????????? get ?????????.*/
//        contactViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(ContactViewModel::class.java)
//    }


    companion object {
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_ContACT_ID"
    }
}
