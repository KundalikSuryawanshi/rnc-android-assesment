package com.kundalik.rncassesment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kundalik.rncassesment.model.Product
import com.kundalik.rncassesment.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val mResponse: MutableLiveData<Response<List<Product>>> = MutableLiveData()


    fun getProducts(brand: String) {
        viewModelScope.launch {
            val response = repository.getProducts(brand)
            mResponse.value = response
        }
    }

    private val _selectedItem = MutableLiveData<Product>()
    val selectedItem: LiveData<Product>
        get() = _selectedItem

    fun selectItem(item: Product) {
        _selectedItem.value = item
    }

}