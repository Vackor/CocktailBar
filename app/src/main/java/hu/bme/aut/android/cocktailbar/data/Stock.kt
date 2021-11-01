package hu.bme.aut.android.cocktailbar.data

object Stock {
    private var items: List<Ingredient>? = null

    public fun setItems(list: List<Ingredient>){
        items = list
    }

    public fun getItems(): List<Ingredient>?{
        return items
    }
}