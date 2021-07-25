package com.htueko.simpletodo.common.data.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    // to provide Firestore Instance
    @Provides
    @Singleton
    fun provideFirestore() = FirebaseFirestore.getInstance()
}
