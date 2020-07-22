package com.example.publicistechnicaltest.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<UI_MODEL> : ViewModel() {
    private val _uiData = MutableLiveData<UI_MODEL>()
    val uiData: LiveData<UI_MODEL>
    get() = _uiData

    protected fun postUiData(uiModel: UI_MODEL) {
        _uiData.postValue(uiModel)
    }
}