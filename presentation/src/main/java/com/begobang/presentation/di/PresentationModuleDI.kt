package com.begobang.presentation.di

import com.begobang.presentation.ui.navigation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModuleDI {
    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun providesNavigationManager() = NavigationManager()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatcher