package tech.gamedev.codeninjas.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.repo.BattleRepo
import tech.gamedev.codeninjas.repo.CreateNewLessonsRepo
import tech.gamedev.codeninjas.repo.LessonRepository
import tech.gamedev.codeninjas.repo.LoginRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.img_example_user)
            .error(R.drawable.img_example_user)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )

    @Singleton
    @Provides
    fun provideLoginRepository() = LoginRepository()

    @Singleton
    @Provides
    fun provideNewLessonRepository() = CreateNewLessonsRepo()

    @Singleton
    @Provides
    fun provideLessonRepository() = LessonRepository()

    @Singleton
    @Provides
    fun provideBattleRepository() = BattleRepo()
}