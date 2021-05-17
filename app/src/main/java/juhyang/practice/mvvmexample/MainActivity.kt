package juhyang.practice.mvvmexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import juhyang.practice.mvvmexample.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.EnumSet.of

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    val viewModel : ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        initRecyclerView()

        binding.mainButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val contactAdapter = ContactAdapter({ contact ->

        }, { contact -> {
            deleteContact(contact)
        }})
        binding.mainRecyclerview.adapter = contactAdapter
        binding.mainRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerview.setHasFixedSize(true)

        viewModel.getAll().observe(this, Observer { contacts ->
            contactAdapter.setContacts(contacts)
        })

    }

//    private fun initViewModel() {
//        /* ViewModel 은 직접적으로 초기화 해주는 것이 아니라 안드로이드 시스템을 통해 생성해준다.
//        * 시스템에서는 만약 이미 생성된 ViewModel 인스턴스가 있다면 이를 반환할 것이므로 메모리 낭비를 줄여준다.
//        * 따라서 ViewModelProvider 를 활용해서 get 해준다.*/
//        contactViewModel = ViewModelProvider(this,
//            ContactViewModel.ContactViewModelFactory(application)
//        ).get(ContactViewModel::class.java)
//
//        /* Observer 를 만들어서 뷰모델이 어떤 Activity / Fragment 생명주기를 관찰할 것인지를 정한다
//        * 이 액티비티가 파괴되는 시점에서 뷰모델도 자동으로 파괴할 것이다.
//        * Kotlin 에서는 람다를 이용해 보다 간편하게 사용할 수 있다. */
//        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
//            // Update UI
//            contactAdapter.setContacts(contacts)
//        })
//    }

    private fun deleteContact(contact : Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete Selected Contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") {_, _ ->
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.delete(contact)
                }
            }

        builder.show()
    }
}
