package tech.gamedev.codeninjas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import kotlinx.android.synthetic.main.item_post.view.*
import kotlinx.coroutines.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.data.models.posts.Post
import tech.gamedev.codeninjas.utils.loadImageUrl


class PostAdapter(options: FirestorePagingOptions<Post>) :
    FirestorePagingAdapter<Post, PostAdapter.PostViewHolder>(options)
{

    var listener: BtnContinueClickedListener? = null
    var nextBtnClicked = false
    var chosenAnswer: String = ""

    inner class PostViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        fun initialize() {
            /*itemView.btnContinueToQuestion.setOnClickListener {

                if(nextBtnClicked && adapterPosition != RecyclerView.NO_POSITION) {
                    nextBtnClicked = false
                    listener?.onLessonClicked()
                } else  {
                    nextBtnClicked = true
                }

            }*/


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))


    }



    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int,
        model: Post
    ) {
        holder.initialize()
       holder.itemView.apply {
           tvPostTitle.text = model.title
           ivPostImage.loadImageUrl(model.imgUrl)
           tvUserName.text = model.userName
       }

    }



    interface BtnContinueClickedListener {
        fun onLessonClicked()
    }


}