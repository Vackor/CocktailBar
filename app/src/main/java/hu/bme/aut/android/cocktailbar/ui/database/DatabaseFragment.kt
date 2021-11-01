package hu.bme.aut.android.cocktailbar.ui.database

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.cocktailbar.DatabaseActivity
import hu.bme.aut.android.cocktailbar.data.Stock
import hu.bme.aut.android.cocktailbar.databinding.FragmentDatabaseBinding

class DatabaseFragment : Fragment() {

    private lateinit var databaseViewModel: DatabaseViewModel
    private var _binding: FragmentDatabaseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databaseViewModel =
            ViewModelProvider(this).get(DatabaseViewModel::class.java)

        _binding = FragmentDatabaseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val items = Stock.getItems()
        var stock: String = ""
        if (items != null) {
            for (item in items) {
                if (item.inStock)
                    stock += item.name + "\n"
            }
        }
        val textView: TextView = binding.textDatabase
        databaseViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = stock
        })

        binding.editButton.setOnClickListener {
            val intent = Intent(this.context, DatabaseActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}