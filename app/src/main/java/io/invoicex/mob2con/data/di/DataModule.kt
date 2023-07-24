package io.invoicex.mob2con.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.invoicex.mob2con.data.repository.DrinksRepositoryImpl
import io.invoicex.mob2con.domain.repository.DrinksRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindDrinksRepository(
        drinksRepositoryImpl: DrinksRepositoryImpl
    ): DrinksRepository
}