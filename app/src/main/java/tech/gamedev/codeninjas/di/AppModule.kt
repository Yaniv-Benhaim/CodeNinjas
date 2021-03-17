package tech.gamedev.codeninjas.di

import android.content.Context
import androidx.paging.PagedList
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.FeaturedItemsAdapter
import tech.gamedev.codeninjas.adapters.PostAdapter
import tech.gamedev.codeninjas.data.models.lessons.LessonAndQuestion
import tech.gamedev.codeninjas.data.models.posts.Post
import tech.gamedev.codeninjas.repo.*
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

    @Singleton
    @Provides
    fun provideFeaturedItemsAdapter() = FeaturedItemsAdapter()

    @Singleton
    @Provides
    fun provideFirestoreInstance() = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideNotificationRepo() = NotificationRepo()

    @Singleton
    @Provides
    fun providePostRepo() = PostRepo()

    @Singleton
    @Provides
    fun providePostOptions(db: FirebaseFirestore): FirestorePagingOptions<Post> {
        val query = db
            .collection("posts")


        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()

        return FirestorePagingOptions.Builder<Post>()
            .setQuery(query, config, Post::class.java)
            .build()
    }

    @Singleton
    @Provides
    fun providePostAdapter(options: FirestorePagingOptions<Post>) = PostAdapter(options)
}