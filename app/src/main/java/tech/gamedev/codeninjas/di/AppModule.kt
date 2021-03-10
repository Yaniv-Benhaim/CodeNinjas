package tech.gamedev.codeninjas.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.repo.BattleRepo
import tech.gamedev.codeninjas.repo.CreateNewLessonsRepo
import tech.gamedev.codeninjas.repo.LessonRepository
import tech.gamedev.codeninjas.repo.LoginRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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

    @Provides
    fun provideFirestore(): FirebaseFirestore =  FirebaseFirestore.getInstance()
}