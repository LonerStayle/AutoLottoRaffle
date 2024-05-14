package kr.loner.autolotto.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.security.SecureRandom

@InstallIn(ViewModelComponent::class)
@Module
class UtilModule {
    @Provides
    fun provideSecureRandom()= SecureRandom()
}