package aguinaga.armando.mymovieapp.di

import aguinaga.armando.mymovieapp.data.paging.UserRepository
import aguinaga.armando.mymovieapp.data.paging.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideUserRepository(userRepository: UserRepositoryImpl): UserRepository
}