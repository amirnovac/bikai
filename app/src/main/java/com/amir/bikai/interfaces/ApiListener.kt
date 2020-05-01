package com.amir.bikai.interfaces

interface ApiListener {

    fun onStarting()

    fun onSucces()

    fun onFailure(message: String)
}