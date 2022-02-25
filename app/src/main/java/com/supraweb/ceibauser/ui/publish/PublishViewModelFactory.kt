package com.supraweb.ceibauser.ui.publish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.supraweb.ceibauser.data.repositories.UserRepository



class PublishViewModelFactory


    (
    private val repository: UserRepository


        ): ViewModelProvider.NewInstanceFactory()



{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PublishViewModel(repository) as T
    }



}