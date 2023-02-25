package com.kundalik.rncassesment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kundalik.rncassesment.repository.Repository
import com.kundalik.rncassesment.viewModel.MainViewModel

class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }

}