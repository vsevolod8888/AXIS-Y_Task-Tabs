package com.example.evstropovtestovoeaxis

import android.app.Application
import com.example.evstropovtestovoeaxis.dagger.Component
import com.example.evstropovtestovoeaxis.dagger.DaggerComponent
import com.example.evstropovtestovoeaxis.dagger.RepozitoryModule

class App : Application() {
    lateinit var ourComponent: Component
    override fun onCreate() {
        super.onCreate()
        ourComponent = DaggerComponent.builder()
            .repozitoryModule(RepozitoryModule(this))
            .build()
    }
    fun getappComponent(): Component {
        return ourComponent
    }
}