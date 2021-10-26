package com.example.evstropovtestovoeaxis.dagger

import com.example.evstropovtestovoeaxis.ui.NotesActivity
import com.example.evstropovtestovoeaxis.ui.fragments.NotesFragment
import com.example.evstropovtestovoeaxis.ui.fragments.UserInfoFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepozitoryModule::class, ViewModelsModule::class])
interface Component {
    fun inject(notesFragment: NotesFragment)
    fun inject(notesActivity: NotesActivity)
    fun inject(userInfoFragment: UserInfoFragment)

}