package tech.gamedev.codeninjas.ui.discuss

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.gamedev.codeninjas.data.models.posts.Post
import tech.gamedev.codeninjas.repo.PostRepo
import javax.inject.Inject

@HiltViewModel
class DiscussViewModel @Inject constructor(private val postRepo: PostRepo) : ViewModel() {

    fun uploadPost(uri: Uri, post: Post) = postRepo.uploadImage(uri, post)
    fun getNewQueryOptions() = postRepo.provideNewOptions()


}