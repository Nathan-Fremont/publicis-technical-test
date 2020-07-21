package com.example.publicistechnicaltest.ui.common

abstract class BaseUiMapper<DOMAIN, UI> {
    abstract fun toUi(domain: DOMAIN): UI
}