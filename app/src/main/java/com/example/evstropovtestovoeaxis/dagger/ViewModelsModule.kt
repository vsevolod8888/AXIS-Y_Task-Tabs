package com.example.evstropovtestovoeaxis.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.evstropovtestovoeaxis.ui.fragments.NotesFragmentViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesFragmentViewModel::class)
    abstract fun bindFragmentCoordViewModel(viewModel: NotesFragmentViewModel?): ViewModel?


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?

}