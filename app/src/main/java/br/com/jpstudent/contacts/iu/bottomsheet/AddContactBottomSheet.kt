package br.com.jpstudent.contacts.iu.bottomsheet

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.jpstudent.contacts.R
import br.com.jpstudent.contacts.databinding.BottomSheetAddContactBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddContactBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetAddContactBinding
    var clickSave: ((String, String) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetThemeTransparent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = true
        dialog.behavior.isHideable = true
        dialog.behavior.isDraggable = true
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            clickSave?.invoke(
                binding.edtName.text.toString(),
                binding.edtNumber.text.toString()
            )
            binding.edtName.text?.clear()
            binding.edtNumber.text?.clear()
        }
    }
}