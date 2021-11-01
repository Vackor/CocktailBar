package hu.bme.aut.android.cocktailbar.ui.toDrink

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDrinkViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is toDrink Fragment"
    }
    val text: LiveData<String> = _text

}