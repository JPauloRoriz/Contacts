package br.com.jpstudent.contacts.iu.activites

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.jpstudent.contacts.databinding.ActivityContactsBinding
import br.com.jpstudent.contacts.iu.bottomsheet.AddContactBottomSheet
import br.com.jpstudent.contacts.iu.fragments.AllContactsFragment
import br.com.jpstudent.contacts.viewmodel.ContactsViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsActivity : AppCompatActivity() {
    private val contactsFragment by lazy { AllContactsFragment.newInstance() }
    private val bottomSheet by lazy { AddContactBottomSheet() }
    private val viewModel: ContactsViewModel by viewModel()

    private lateinit var binding: ActivityContactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFragment(contactsFragment)
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {

        binding.btnOppenBottomSheet.setOnClickListener {
            viewModel.tapOnAddContact()
        }


        bottomSheet.clickSave = { name, number ->
            viewModel.tapOnSave(name, number)
        }

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.selectTab(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setupObservers() {
        viewModel.oppenBottomSheetLiveData.observe(this) {
            bottomSheet.show(supportFragmentManager, null)
        }
        viewModel.successAddContactLiveData.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            bottomSheet.dismiss()
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(binding.content.id, fragment)
        fragmentTransition.commit()
    }
}