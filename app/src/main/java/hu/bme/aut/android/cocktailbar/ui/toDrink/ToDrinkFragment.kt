package hu.bme.aut.android.cocktailbar.ui.toDrink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.cocktailbar.databinding.FragmentHomeBinding
import hu.bme.aut.android.cocktailbar.databinding.FragmentToDrinkBinding

class ToDrinkFragment: Fragment() {

    private lateinit var toDrinkViewModel: ToDrinkViewModel
    private var _binding: FragmentToDrinkBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toDrinkViewModel =
            ViewModelProvider(this).get(ToDrinkViewModel::class.java)

        _binding = FragmentToDrinkBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textToDrink
        toDrinkViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}