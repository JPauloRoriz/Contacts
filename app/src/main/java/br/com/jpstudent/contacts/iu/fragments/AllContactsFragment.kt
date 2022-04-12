package br.com.jpstudent.contacts.iu.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.jpstudent.contacts.databinding.FragmentAllContactsBinding
import br.com.jpstudent.contacts.iu.adapter.ContactsAdapter
import br.com.jpstudent.contacts.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AllContactsFragment : Fragment() {

    private lateinit var binding: FragmentAllContactsBinding
    private val adapter by lazy { ContactsAdapter() }
    private val viewModel: ContactsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllContactsBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvContacts.adapter = adapter
        setupListeners()
        setupObservers()

    }


    private fun setupListeners() {
        adapter.clickStar = {
            viewModel.tapOnChangeFavorite(it)
        }
    }

    private fun setupObservers() {
        viewModel.contactsLiveData.observe(viewLifecycleOwner) {
            adapter.contacts = it
        }
    }

    companion object {
        fun newInstance(): AllContactsFragment {
            val args = Bundle()
            val fragment = AllContactsFragment()
            fragment.arguments = args
            return fragment
        }
    }
}