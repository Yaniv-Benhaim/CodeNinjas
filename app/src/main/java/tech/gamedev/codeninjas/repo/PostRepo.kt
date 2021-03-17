package tech.gamedev.codeninjas.repo

import android.net.Uri
import android.util.Log
import androidx.paging.PagedList
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import tech.gamedev.codeninjas.data.models.posts.Post
import java.lang.Exception
import kotlin.random.Random

class PostRepo {
    // Create a storage reference from our app
    private val storageRef = FirebaseStorage.getInstance().reference
    private val db = FirebaseFirestore.getInstance().collection("posts")
    private val UID = Random.nextInt()
    private val postImageRef = storageRef.child("images/$UID.jpg")
    private val auth = FirebaseAuth.getInstance()


    fun uploadImage(uri: Uri, post: Post) = CoroutineScope(Dispatchers.IO).launch {
        try {
            var uploadTask = postImageRef.putFile(uri)
            val urlTask = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                postImageRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    post.imgUrl = downloadUri.toString()
                    post.userName = auth.currentUser?.displayName!!
                    uploadPostToFirestore(post)
                } else {
                    Log.d("UPLOAD", "FAILED UPLOAD AT LINE 38: PostRepo")

                }
            }
        } catch (e: Exception) {
            Log.d("UPLOAD", e.toString())
        }

    }

    private fun uploadPostToFirestore(post: Post) = CoroutineScope(Dispatchers.IO).launch {
        try {
            db.add(post).await()
            Log.d("UPLOAD", post.toString())
        } catch (e: Exception) {
            Log.d("UPLOAD", e.toString())
        }

    }

    fun provideNewOptions(): FirestorePagingOptions<Post> {
        val query = db.orderBy("title")
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()

        return FirestorePagingOptions.Builder<Post>()
            .setQuery(query, config, Post::class.java)
            .build()
    }
}