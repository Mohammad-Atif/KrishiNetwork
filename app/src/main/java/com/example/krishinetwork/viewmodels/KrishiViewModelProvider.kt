package com.example.krishinetwork.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.krishinetwork.repository.KrishiRepository

class KrishiViewModelProvider(
    val krishiRepository: KrishiRepository
):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KrishiViewModel(krishiRepository) as T
    }
}