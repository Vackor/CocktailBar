package hu.bme.aut.android.cocktailbar.database_fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.cocktailbar.data.Ingredient
import hu.bme.aut.android.cocktailbar.databinding.DialogNewIngredientBinding

class newIngredientDialogFragment: DialogFragment() {

    interface NewIngredientDialogListener {
        fun onIngredientCreated(newItem: Ingredient)
    }

    companion object {
        const val TAG = "NewIngredientDialogFragment"
    }

    private lateinit var listener: NewIngredientDialogListener
    private lateinit var binding: DialogNewIngredientBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewIngredientDialogListener
            ?: throw RuntimeException(
                "Activity must implement the NewIngredientDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewIngredientBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireContext())
            .setTitle("Add new ingredient")
            .setView(binding.root)
            .setPositiveButton("Add") { dialogInterface, i ->
                if (isValid()) {
                    listener.onIngredientCreated(getIngredient())
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }

    private fun isValid() = binding.etName.text.isNotEmpty()

    private fun getIngredient() = Ingredient(
        name = binding.etName.text.toString(),
        inStock = binding.cbInStock.isChecked
    )

}
